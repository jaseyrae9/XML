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
		System.out.println("Pronasli smo hotel");
		
		if (!opt.isPresent()) {
			// TODO: exception
		}
		System.out.println("Hotel postoji");
		
		Hotel hotel = opt.get();
		System.out.println("Hotel ucitan");
		
		Room r = new Room(newRoomRequest.getRoom());
		System.out.println("Naparvljena soba");
		
		r.setHotel(hotel);
		System.out.println("Postavljen hotel");
		
		Room ret = save(r);
		
		System.out.println("Soba sacuvana");
		return ret;
	}
	
	
	public Room save(Room room) {
		return roomRepository.save(room);
	}

}
