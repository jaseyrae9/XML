package rs.ac.uns.ftn.xml.team17.authservice.service.entityservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.authservice.model.entity.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.authservice.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	public Hotel getHotel(Integer id) {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		
		if(!hotel.isPresent()) {
			// TODO: exception
		}
		
		return hotel.get();
	}	
	
	public Optional<Hotel> findHotel(Integer id) {
		return hotelRepository.findById(id);
	}
}
