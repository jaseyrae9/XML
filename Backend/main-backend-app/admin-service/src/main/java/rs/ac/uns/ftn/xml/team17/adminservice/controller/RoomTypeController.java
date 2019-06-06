package rs.ac.uns.ftn.xml.team17.adminservice.controller;

import java.util.List;

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
		List<RoomTypeDTO> ret = roomTypeService.getRoomTypes();
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/**
	 * Returns data about room type with selected id.
	 * 
	 * @param id - id of the room type
	 * @return 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAdditionalService(@PathVariable Integer id){
		RoomType roomType = roomTypeService.getRoomType(id);
		RoomTypeDTO ret = new RoomTypeDTO(roomType);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	} 	
	
	/**
	 * Adds a new room type.
	 * 
	 * @param roomTypeDTO - informations of the room type
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RoomTypeDTO> addRoomType(@Valid @RequestBody RoomTypeDTO roomTypeDTO) {
		RoomType roomType = new RoomType(roomTypeDTO.getName());
		return new ResponseEntity<>(new RoomTypeDTO(roomTypeService.save(roomType)), HttpStatus.CREATED);
	}
	
	/**
	 * Edits the existing room type with selected id.
	 * 
	 * @param id - id of the room type
	 * @param roomTypeDTO - contains new information for room type
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RoomTypeDTO> editRoomType(@PathVariable Integer id, @Valid @RequestBody RoomTypeDTO roomTypeDTO) {
		RoomTypeDTO ret = new RoomTypeDTO(roomTypeService.editRoomType(roomTypeDTO));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	/**
	 * Deletes the existing room type with selected id.
	 * 
	 * @param id - id of the room type
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRoomType(@PathVariable Integer id) {
		roomTypeService.deleteRoomType(id);
		return new ResponseEntity<>(HttpStatus.OK);	
	}
}
