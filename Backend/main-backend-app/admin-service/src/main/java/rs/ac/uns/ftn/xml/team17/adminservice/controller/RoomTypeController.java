package rs.ac.uns.ftn.xml.team17.adminservice.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.uns.ftn.xml.team17.adminservice.dto.RoomTypeDTO;
import rs.ac.uns.ftn.xml.team17.adminservice.model.roomType.RoomType;
import rs.ac.uns.ftn.xml.team17.adminservice.service.RoomTypeService;

@Controller
@RequestMapping("/roomType")
public class RoomTypeController {
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	/**
	 * Returns DTO objects for room types. Objects contain id and name.
	 * 
	 * @return information about all room types.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getRoomTypes(){
		return new ResponseEntity<>(roomTypeService.findAll(), HttpStatus.OK);
	}

	/**
	 * Returns data about room type with selected id.
	 * 
	 * @param id - id of room type
	 * @return 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAdditionalService(@PathVariable Integer id){
		return new ResponseEntity<>(roomTypeService.getRoomType(id), HttpStatus.OK);
	} 	
	
	/**
	 * Adds new room type.
	 * 
	 * @param roomTypeDTO - informations of room type
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RoomTypeDTO> addRoomType(@Valid @RequestBody RoomTypeDTO roomTypeDTO) {
		RoomType roomType = new RoomType(roomTypeDTO.getName());
		return new ResponseEntity<>(new RoomTypeDTO(roomTypeService.save(roomType)), HttpStatus.CREATED);
	}
	
	/**
	 * Edit existing room type.
	 * 
	 * @param id - id of room type
	 * @param roomTypeDTO - contains new information for room type
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RoomTypeDTO> editRoomType(@PathVariable Integer id, @Valid @RequestBody RoomTypeDTO roomTypeDTO) {
		Optional<RoomType> opt = roomTypeService.findRoomType(roomTypeDTO.getId());
		
		if(!opt.isPresent()) {
			// TODO: exception
		}
		
		// set name and description
		opt.ifPresent(roomType -> {
			roomType.setName(roomTypeDTO.getName());
		});
		
		return new ResponseEntity<>(new RoomTypeDTO(roomTypeService.save(opt.get())), HttpStatus.OK);
	}
}
