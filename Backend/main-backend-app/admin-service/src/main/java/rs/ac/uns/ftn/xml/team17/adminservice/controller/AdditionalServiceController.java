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

import rs.ac.uns.ftn.xml.team17.adminservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.adminservice.model.additionalService.AdditionalService;
import rs.ac.uns.ftn.xml.team17.adminservice.service.AdditionalServiceService;

@Controller
@RequestMapping("/additionalService")
public class AdditionalServiceController {
	@Autowired
	private AdditionalServiceService additionalServiceService;

	/**
	 *  Returns active objects for additional services. Objects contain id and name.
	 *  
	 * @return information about active additional services.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAdditionalServices(){		
		Iterable<AdditionalService> ret = additionalServiceService.getServices();
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	/**
	 * Returns data about the additional service with selected id.
	 * 
	 * @param id - id of the additional service
	 * @return 
	 * @throws NotFoundException 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAdditionalService(@PathVariable Integer id) throws NotFoundException{
		AdditionalService additionalService = additionalServiceService.getAdditionalService(id);		
		return new ResponseEntity<>(additionalService, HttpStatus.OK);
	}
	
	/**
	 * Adds a new additional service.
	 * 
	 * @param additionalService  - informations of the additional service
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AdditionalService> addAdditionalService(@Valid @RequestBody AdditionalService ad) {
		AdditionalService additionalService = new AdditionalService(ad.getName());
		return new ResponseEntity<>(additionalServiceService.save(additionalService), HttpStatus.CREATED);
	}
	
	/**
	 * Edits the existing additional service with selected id.
	 * 
	 * @param id - id of the additional service
	 * @param additionalServiceDTO - contains new informations for additional service
	 * @return updated additional service
	 * @throws NotFoundException 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<AdditionalService> editAdditionalService(@PathVariable Integer id, @RequestBody AdditionalService additionalServiceDTO) throws NotFoundException {
		AdditionalService ret = additionalServiceService.editAddtionalService(id, additionalServiceDTO);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	/**
	 * Deletes the existing additional service with selected it.
	 * 
	 * @param id - id of the additional service
	 * @return
	 * @throws NotFoundException 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAdditionalService(@PathVariable Integer id) throws NotFoundException {
		AdditionalService ret = additionalServiceService.deleteAdditionalService(id);
		return new ResponseEntity<>(ret, HttpStatus.OK);	
	}


}
