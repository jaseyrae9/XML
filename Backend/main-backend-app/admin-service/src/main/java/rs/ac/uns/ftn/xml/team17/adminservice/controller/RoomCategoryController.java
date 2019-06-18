package rs.ac.uns.ftn.xml.team17.adminservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.uns.ftn.xml.team17.adminservice.model.roomCategory.RoomCategory;
import rs.ac.uns.ftn.xml.team17.adminservice.service.RoomCategoryService;

@Controller
@RequestMapping("/roomCategory")
public class RoomCategoryController {

	@Autowired
	private RoomCategoryService roomCategoryService;

	/**
	 *  Returns active objects for room categories. Objects contain id, number of stars and description.
	 * 
	 * @return information about all room categories.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getRoomCategories() {		
		Iterable<RoomCategory> ret = roomCategoryService.getRoomCategories();
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	/** 
	 * Returns data about the room category with selected id.
	 * 
	 * @param id - id of the room category.
	 * @return 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getRoomCategory(@PathVariable Integer id){
		RoomCategory roomCategory = roomCategoryService.getRoomCategory(id);
		return new ResponseEntity<>(roomCategory, HttpStatus.OK);
	} 
	
	/**
	 * Adds a new room category.
	 * 
	 * @param roomCategoryDTO - informations of the room category
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RoomCategory> addRoomCategory(@Valid @RequestBody RoomCategory roomCategoryDTO) {
		RoomCategory roomCategory = new RoomCategory(roomCategoryDTO.getNumberOfStars(), roomCategoryDTO.getDescription());
		return new ResponseEntity<>(roomCategoryService.save(roomCategory), HttpStatus.CREATED);
	}
	
	/**
	 * Edits the existing room category with selected id.
	 * 
	 * @param id - id of the room category
	 * @param roomCategoryDTO - contains new information for room category
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RoomCategory> editRoomCategory(@PathVariable Integer id, @Valid @RequestBody RoomCategory roomCategoryDTO) {
		RoomCategory ret = roomCategoryService.editRoomCategory(roomCategoryDTO);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	/**
	 * Deletes the existing room category with selected id.
	 * 
	 * @param id - id of the additional service
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRoomCategory(@PathVariable Integer id) {
		RoomCategory ret = roomCategoryService.deleteRoomCategory(id);
		return new ResponseEntity<>(ret,HttpStatus.OK);	
	}
}
