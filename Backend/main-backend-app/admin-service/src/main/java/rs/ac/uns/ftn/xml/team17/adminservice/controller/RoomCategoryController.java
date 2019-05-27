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

import rs.ac.uns.ftn.xml.team17.adminservice.dto.RoomCategoryDTO;
import rs.ac.uns.ftn.xml.team17.adminservice.model.roomCategory.RoomCategory;
import rs.ac.uns.ftn.xml.team17.adminservice.service.RoomCategoryService;

@Controller
@RequestMapping("/roomCategory")
public class RoomCategoryController {

	@Autowired
	private RoomCategoryService roomCategoryService;

	/**
	 * Returns DTO objects for room categories. Objects contain id, number of stars and description.
	 * 
	 * @return information about all room categories.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getRoomCategories(){
		return new ResponseEntity<>(roomCategoryService.findAll(), HttpStatus.OK);
	}
	
	/** 
	 * Returns data about room category with selected id.
	 * 
	 * @param id - id of room category.
	 * @return 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getRoomCategory(@PathVariable Integer id){
		Optional<RoomCategory> roomCategory = roomCategoryService.findRoomCategory(id);
		if(!roomCategory.isPresent()) {
			// TODO: exception
		}
		return new ResponseEntity<>(roomCategoryService.getRoomCategory(id), HttpStatus.OK);
	} 
	
	/**
	 * Adds new room category.
	 * 
	 * @param roomCategoryDTO - informations of room category
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RoomCategoryDTO> addRoomCategory(@Valid @RequestBody RoomCategoryDTO roomCategoryDTO) {
		RoomCategory roomCategory = new RoomCategory(roomCategoryDTO.getNumberOfStars(), roomCategoryDTO.getDescription());
		return new ResponseEntity<>(new RoomCategoryDTO(roomCategoryService.save(roomCategory)), HttpStatus.CREATED);
	}
	
	/**
	 * Edit existing room category.
	 * 
	 * @param id- id of room category
	 * @param roomCategoryDTO - contains new information for room category
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RoomCategoryDTO> editRoomCategory(@PathVariable Integer id, @Valid @RequestBody RoomCategoryDTO roomCategoryDTO) {
		Optional<RoomCategory> opt = roomCategoryService.findRoomCategory(roomCategoryDTO.getId());
		
		if(!opt.isPresent()) {
			// TODO: exception
		}
		
		// set number of stars and description
		opt.ifPresent(roomCategory -> {
			roomCategory.setNumberOfStars(roomCategoryDTO.getNumberOfStars());
			roomCategory.setDescription(roomCategoryDTO.getDescription());
		});
		
		return new ResponseEntity<>(new RoomCategoryDTO(roomCategoryService.save(opt.get())), HttpStatus.OK);

	}
}
