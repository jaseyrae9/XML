package rs.ac.uns.ftn.xml.team17.roomservice.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.room.RoomPriceDTO;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.addimage.AddImageRequest;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.newroom.NewRoomRequest;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.price.SetPriceRequest;
import rs.ac.uns.ftn.xml.team17.roomservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.roomservice.model.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.roomservice.model.image.Image;
import rs.ac.uns.ftn.xml.team17.roomservice.model.price.Price;
import rs.ac.uns.ftn.xml.team17.roomservice.model.room.Room;
import rs.ac.uns.ftn.xml.team17.roomservice.repository.PriceRepository;
import rs.ac.uns.ftn.xml.team17.roomservice.repository.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	/**
	 * Finds room with given id.
	 * @param id - room id
	 * @return
	 * @throws NotFoundException 
	 */
	public Room getRoom(Integer id) throws NotFoundException {
		Optional<Room> opt = roomRepository.findById(id);		
		if (!opt.isPresent()) {
			throw new NotFoundException(id, Room.class.getSimpleName());
		}
		return opt.get();
	}
	
	/**
	 * Finds room belonging to given hotel.
	 * @param room
	 * @param hotel
	 * @return
	 * @throws NotFoundException
	 */
	public Room getRoom(Integer room, Integer hotel) throws NotFoundException {
		Optional<Room> opt = roomRepository.getRoom(room, hotel);	
		if (!opt.isPresent()) {
			throw new NotFoundException(room, Room.class.getSimpleName());
		}
		return opt.get();
	}
	
	/**
	 * Saves room to database.
	 * @param room
	 * @return
	 */
	public Room save(Room room) {
		return roomRepository.save(room);
	}
	
	/**
	 * Returns prices for room between two given dates. Returns only days for when special prices are set.
	 * @param id - room id
	 * @param from - start date
	 * @param to - end date
	 * @return - list of prices, containting date and amount
	 */
	public List<RoomPriceDTO> getPrice(Integer id, Date from, Date to){
		return priceRepository.getRoomPrices(id, from, to);
	}
	
	public Room addRoom(NewRoomRequest newRoomRequest, Integer hotelId) {				
		Room r = new Room(newRoomRequest.getRoom());
		r.setHotel(new Hotel(hotelId));
		Room ret = save(r);
		return ret;
	}	

	
	public void addPrice(SetPriceRequest setPriceRequest, Integer hotelId) throws NotFoundException {	
		Room room = getRoom(setPriceRequest.getId(), hotelId);
		List<Price> prices = this.priceRepository.getPrices(setPriceRequest.getId(),  setPriceRequest.getDateFrom(),  setPriceRequest.getDateTo());
		
		LocalDate start = setPriceRequest.getDateFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = setPriceRequest.getDateTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		for (LocalDate date = start; date.isBefore(end) || date.isEqual(end); date = date.plusDays(1))
		{		
			Date priceDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		    Optional<Price> priceOpt = prices.stream().filter(o -> o.getDate().equals(priceDate)).findFirst();
		    if(priceOpt.isPresent()) {
		    	priceOpt.get().setAmount(setPriceRequest.getPrice());
		    }
		    else {
		    	Price price = new Price(room, setPriceRequest.getPrice(), priceDate);
		    	prices.add(price);
		    }
		}	
		
		priceRepository.saveAll(prices);
	}

	public void addImage(AddImageRequest addImageRequest, Integer hotelId) throws NotFoundException {
		Room r = getRoom(addImageRequest.getId(), hotelId);
		
		if(addImageRequest.isMainImage()) {
			for(Image i : r.getImages()) {
				if(i.getMainImage()) {
					i.setMainImage(false);
				}
			}
		}		
		Image newImage = new Image(addImageRequest, r);		
		r.addImage(newImage);
		roomRepository.save(r);
	}

}
