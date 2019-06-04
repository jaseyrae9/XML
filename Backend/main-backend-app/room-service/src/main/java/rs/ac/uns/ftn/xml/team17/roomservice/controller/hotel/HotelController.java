package rs.ac.uns.ftn.xml.team17.roomservice.controller.hotel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel.HotelFull;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel.HotelPreview;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<HotelPreview>> getHotel(Pageable page){
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{hotelId}")
	public ResponseEntity<HotelFull> getHotel(@PathVariable Integer hotelId){
		return null;
	}
}
