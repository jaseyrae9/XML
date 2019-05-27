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

import rs.ac.uns.ftn.xml.team17.adminservice.dto.AdditionalServiceDTO;
import rs.ac.uns.ftn.xml.team17.adminservice.model.additionalService.AdditionalService;
import rs.ac.uns.ftn.xml.team17.adminservice.service.AdditionalServiceService;

@Controller
@RequestMapping("/additionalService")
public class AdditionalServiceController {
	@Autowired
	private AdditionalServiceService additionalServiceService;

	/**
	 *  Returns DTO objects for additional services. Objects contain id and name.
	 *  
	 * @return information about all additional services.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAdditionalServices(){
		return new ResponseEntity<>(additionalServiceService.findAll(), HttpStatus.OK);
	}
	
	/**
	 * Returns data about the additional service with selected id.
	 * 
	 * @param id - id of additional service
	 * @return 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAdditionalService(@PathVariable Integer id){
		Optional<AdditionalService> additionalService = additionalServiceService.findAdditionalService(id);
		if(!additionalService.isPresent()) {
			// TODO: exception
		}
		return new ResponseEntity<>(additionalServiceService.getAdditionalService(id), HttpStatus.OK);
	}
	
	/**
	 * Adds a new additional service.
	 * 
	 * @param additionalServiceDTO  - informations of additional service
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AdditionalServiceDTO> addAdditionalService(@Valid @RequestBody AdditionalServiceDTO additionalServiceDTO) {
		AdditionalService additionalService = new AdditionalService(additionalServiceDTO.getName());
		return new ResponseEntity<>(new AdditionalServiceDTO(additionalServiceService.save(additionalService)), HttpStatus.CREATED);
	}
	
	/**
	 * Edits the existing additional service with selected id.
	 * 
	 * @param id - id of additional service
	 * @param additionalServiceDTO - contains new informations for additional service
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<AdditionalServiceDTO> editAdditionalService(@PathVariable Integer id, @Valid @RequestBody AdditionalServiceDTO additionalServiceDTO) {
		Optional<AdditionalService> opt = additionalServiceService.findAdditionalService(additionalServiceDTO.getId());
		
		if(!opt.isPresent()) {
			// TODO: exception
		}
		
		// set name and description
		opt.ifPresent(additionalService -> {
			additionalService.setName(additionalServiceDTO.getName());
		});
		
		return new ResponseEntity<>(new AdditionalServiceDTO(additionalServiceService.save(opt.get())), HttpStatus.OK);
	}
	
	/**
	 * Deletes the existing additional service.
	 * 
	 * @param id - id of selected additional service
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAdditionalService(@PathVariable Integer id) {
		additionalServiceService.deleteAdditionalService(id);
		return new ResponseEntity<>(HttpStatus.OK);	
	}


}
