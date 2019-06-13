package rs.ac.uns.ftn.xml.team17.roomservice.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.newroom.NewRoomRequest;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.price.SetPriceRequest;
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
		System.out.println("Hotel postoji");
		
		Hotel hotel = opt.get();
		System.out.println("Hotel ucitan2");
		
		System.out.println(newRoomRequest);
		if(newRoomRequest.getRoom() == null) {
			System.out.println("Izgleda se nije parsirala soba kako trreba");
		}
		
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


	public void addPrice(SetPriceRequest setPriceRequest) {
		Optional<Room> opt = roomRepository.findById(setPriceRequest.getId());
		
		if (!opt.isPresent()) {
			// TODO: exception
		}
		
		System.out.println("Soba postoji103");
		
		Room room = opt.get();
		
		System.out.println(setPriceRequest.getDateFrom());
		LocalDate start = setPriceRequest.getDateFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = setPriceRequest.getDateTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		System.out.println("Ispisivanje datuma" + start);
		for (LocalDate date = start; date.isBefore(end) || date.isEqual(end); date = date.plusDays(1))
		{
		    System.out.println(date);
		    room.setPrice(date, setPriceRequest.getPrice());
		}
		
		System.out.println("Prosli kroz for");
		roomRepository.save(room);
	}

}
