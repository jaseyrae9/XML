package rs.ac.uns.ftn.xml.team17.roomservice.controller.hotel;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel.HotelDTO;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel.HotelDTOResponse;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel.HotelFull;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel.HotelPreview;
import rs.ac.uns.ftn.xml.team17.roomservice.model.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.roomservice.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	/**
	 *  Returns DTO objects for hotels. Objects contain id, name and address.
	 *  
	 * @return information about all hotels.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<HotelPreview>> getHotel(Pageable page) {
		Page<HotelPreview> ret = hotelService.getHotels(page);
		return ResponseEntity.ok(ret);
	}

	/**
	 * Returns data about the hotel with selected id.
	 * 
	 * @param hotelId - id of the hotel
	 * @return 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{hotelId}")
	public ResponseEntity<HotelFull> getHotel(@PathVariable Integer hotelId) {
		Hotel hotel = hotelService.getHotel(hotelId);
		return new ResponseEntity<>(new HotelFull(hotel), HttpStatus.OK);
	}
	
	/**
	 * Adds a new hotel.
	 * 
	 * @param hotelDTO - informations of the hotel
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<HotelDTOResponse> addHotel(@Valid @RequestBody HotelDTO hotelDTO) {
		Hotel hotel = new Hotel(hotelDTO.getName(), hotelDTO.getAddress(), hotelDTO.getPib());
		return new ResponseEntity<>(new HotelDTOResponse(hotelService.save(hotel)), HttpStatus.CREATED);
	}
}
