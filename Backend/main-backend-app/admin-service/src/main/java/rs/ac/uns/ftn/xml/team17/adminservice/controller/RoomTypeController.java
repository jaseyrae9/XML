package rs.ac.uns.ftn.xml.team17.adminservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.uns.ftn.xml.team17.adminservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.adminservice.model.roomType.RoomType;
import rs.ac.uns.ftn.xml.team17.adminservice.service.RoomTypeService;

@Controller
@RequestMapping("/roomType")
public class RoomTypeController {
	
	@Autowired
	private RoomTypeService roomTypeService;
	
	/**
	 * Returns active objects for room types. Objects contain id and name.
	 * 
	 * @return information about all room types.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getRoomTypes(){		
		Iterable<RoomType> ret = roomTypeService.getRoomTypes();
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/**
	 * Returns data about room type with selected id.
	 * 
	 * @param id - id of the room type
	 * @return 
	 * @throws NotFoundException 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAdditionalService(@PathVariable Integer id) throws NotFoundException{
		RoomType roomType = roomTypeService.getRoomType(id);
		return new ResponseEntity<>(roomType, HttpStatus.OK);
	} 	
	
	/**
	 * Adds a new room type.
	 * 
	 * @param roomTypeDTO - informations of the room type
	 * @return
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RoomType> addRoomType(@Valid @RequestBody RoomType roomTypeDTO) {
		RoomType roomType = new RoomType(roomTypeDTO.getName());
		return new ResponseEntity<>(roomTypeService.save(roomType), HttpStatus.CREATED);
	}
	
	/**
	 * Edits the existing room type with selected id.
	 * 
	 * @param id - id of the room type
	 * @param roomTypeDTO - contains new information for room type
	 * @return
	 * @throws NotFoundException 
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RoomType> editRoomType(@PathVariable Integer id, @Valid @RequestBody RoomType roomTypeDTO) throws NotFoundException {
		RoomType ret = roomTypeService.editRoomType(id, roomTypeDTO);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	/**
	 * Deletes the existing room type with selected id.
	 * 
	 * @param id - id of the room type
	 * @return
	 * @throws NotFoundException 
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRoomType(@PathVariable Integer id) throws NotFoundException {
		RoomType ret = roomTypeService.deleteRoomType(id);
		return new ResponseEntity<>(ret, HttpStatus.OK);	
	}
}
