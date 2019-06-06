package rs.ac.uns.ftn.xml.team17.roomservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel.HotelPreview;
import rs.ac.uns.ftn.xml.team17.roomservice.model.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.roomservice.repository.HotelRepository;

@Service
public class HotelService {
	@Autowired
	private HotelRepository hotelRepository;

	public Hotel getHotel(Integer id) {
		Optional<Hotel> opt = hotelRepository.findById(id);
		if (!opt.isPresent()) {
			// TODO: exception
		}
		return opt.get();
	}

	public Iterable<Hotel> findAll() {
		return hotelRepository.findAll();
	}

	public Page<Hotel> findAll(Pageable page) {
		return hotelRepository.findAll(page);
	}

	public Hotel save(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public Page<HotelPreview> getHotels(Pageable page) {
		Page<Hotel> hotels = hotelRepository.findAll(page);
		
		// convert hotels to DTO
		List<HotelPreview> hotelsDTO = new ArrayList<>();
		for (Hotel hotel : hotels) {
			hotelsDTO.add(new HotelPreview(hotel));
		}

		Page<HotelPreview> ret = new PageImpl<>(hotelsDTO, hotels.getPageable(), hotels.getTotalElements());
		return ret;
	}

}
