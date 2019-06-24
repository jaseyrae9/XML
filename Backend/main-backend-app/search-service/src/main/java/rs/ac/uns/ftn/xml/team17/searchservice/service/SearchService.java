package rs.ac.uns.ftn.xml.team17.searchservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.searchservice.dto.RoomPreview;
import rs.ac.uns.ftn.xml.team17.searchservice.dto.SearchRequest;
import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.room.Room;
import rs.ac.uns.ftn.xml.team17.searchservice.model.temporary.Location;
import rs.ac.uns.ftn.xml.team17.searchservice.repository.RoomRepository;

@Service
public class SearchService {
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private PriceService priceService;
	@Autowired
	private LocationService locationService;
	
	public List<RoomPreview> search(SearchRequest searchRequest){	
		//if distance is set, ignore location
		if(searchRequest.getDistanceFromLocation() != null) {
			searchRequest.setLocation("");
		}
		else {
			searchRequest.setDistanceFromLocation(Double.MAX_VALUE);
		}
		
		//perform search
		List<Room> rooms = roomRepository.searchRooms(
				searchRequest.getStart(),
				searchRequest.getEnd(),
				searchRequest.getLocation() + "%",
				searchRequest.getNumberOfPeople(),
				searchRequest.getType(),
				searchRequest.getCategory(),
				searchRequest.getCancelationDays(),
				searchRequest.getAdditionalServices(),
				searchRequest.getAdditionalServicesSize()
		);

		//calculate location for distances
		Location location = null;
		if( rooms.size() != 0 ) {
			location = locationService.getCoordinates(searchRequest.getLocation());
		}
		
		//convert results
		List<RoomPreview> ret = new ArrayList<RoomPreview>();
		for(Room room:rooms) {
			RoomPreview rp = new RoomPreview(room);
			rp.setTotalStayPrice(this.priceService.calculatePrice(room, searchRequest.getStart(), searchRequest.getEnd()));
			rp.setDistance(this.locationService.distance(location, new Location(room.getAddress().getLat(), room.getAddress().getLng())));
			if(rp.getDistance() <= searchRequest.getDistanceFromLocation()) {				
				ret.add(rp);
			}
		}		
		
		return ret;
	}

}
