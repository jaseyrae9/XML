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
		List<AdditionalServiceDTO> ret = additionalServiceService.getServices();
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	/**
	 * Returns data about the additional service with selected id.
	 * 
	 * @param id - id of the additional service
	 * @return 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAdditionalService(@PathVariable Integer id){
		AdditionalService additionalService = additionalServiceService.getAdditionalService(id);		
		AdditionalServiceDTO ret = new AdditionalServiceDTO(additionalService);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	/**
	 * Adds a new additional service.
	 * 
	 * @param additionalServiceDTO  - informations of the additional service
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
	 * @param id - id of the additional service
	 * @param additionalServiceDTO - contains new informations for additional service
	 * @return updated additional service
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<AdditionalServiceDTO> editAdditionalService(@PathVariable Integer id, @Valid @RequestBody AdditionalServiceDTO additionalServiceDTO) {
		AdditionalServiceDTO ret = new AdditionalServiceDTO(additionalServiceService.editAddtionalService(additionalServiceDTO));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	/**
	 * Deletes the existing additional service with selected it.
	 * 
	 * @param id - id of the additional service
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAdditionalService(@PathVariable Integer id) {
		additionalServiceService.deleteAdditionalService(id);
		return new ResponseEntity<>(HttpStatus.OK);	
	}


}
