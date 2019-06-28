package rs.ac.uns.ftn.xml.team17.roomservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel.HotelBasicsDTO;
import rs.ac.uns.ftn.xml.team17.roomservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.roomservice.model.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.roomservice.repository.HotelRepository;

@Service
public class HotelService {
	@Autowired
	private HotelRepository hotelRepository;

	public Hotel getHotel(Integer id) throws NotFoundException {
		Optional<Hotel> opt = hotelRepository.findById(id);
		if (!opt.isPresent()) {
			throw new NotFoundException(id, Hotel.class.getSimpleName());
		}
		return opt.get();
	}

	public Hotel save(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public Page<HotelBasicsDTO> getHotels(Pageable page) {		
		return hotelRepository.findAllBasics(page);
	}

}
