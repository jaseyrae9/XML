package rs.ac.uns.ftn.xml.team17.adminservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.adminservice.model.roomType.RoomType;
import rs.ac.uns.ftn.xml.team17.adminservice.repository.RoomTypeRepository;

@Service
public class RoomTypeService {

	@Autowired
	private RoomTypeRepository roomTypeRepository;
	
	public RoomType getRoomType(Integer id) {
		Optional<RoomType> roomType = roomTypeRepository.findById(id);
		if(!roomType.isPresent()) {
			//TODO: Ovde baciti exception
		}
		return roomType.get();
	}
	
	public RoomType save(RoomType roomType) {
		return roomTypeRepository.save(roomType);
	}
	
	public Iterable<RoomType> findAll(){
		return roomTypeRepository.findAll();
	}
	
	public Optional<RoomType> findRoomType(Integer id) {
		return roomTypeRepository.findById(id);
	}
		
	// TODO: Logicko?
//	public void delete(RoomType roomType) {
//		roomTypeRepository.delete(roomType);
//	}
	
}
