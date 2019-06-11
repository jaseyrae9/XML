package rs.ac.uns.ftn.xml.team17.roomservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.room.NewRoomRequest;
import rs.ac.uns.ftn.xml.team17.roomservice.model.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.roomservice.model.room.Room;
import rs.ac.uns.ftn.xml.team17.roomservice.repository.HotelRepository;
import rs.ac.uns.ftn.xml.team17.roomservice.repository.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	// TODO: morace se proveriti da li pravi admin dodaje sobu
	public Room addRoom(NewRoomRequest newRoomRequest) {
		
		Optional<Hotel> opt = hotelRepository.findById(newRoomRequest.getId());
		
		if (!opt.isPresent()) {
			// TODO: exception
		}
		
		Hotel hotel = opt.get();
		
		Room r = new Room(newRoomRequest.getRoom());
		r.setHotel(hotel);
		
		return save(r);
	}
	
	
	public Room save(Room room) {
		return roomRepository.save(room);
	}

}
