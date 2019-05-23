package rs.ac.uns.ftn.xml.team17.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.uns.ftn.xml.team17.adminservice.service.AdditionalServiceService;

@Controller
@RequestMapping("/additionalService")
public class AdditionalServiceController {
	@Autowired
	private AdditionalServiceService additionalServiceService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAdditionalServices(){
		return new ResponseEntity<>(additionalServiceService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAdditionalService(@PathVariable Integer id){
		return new ResponseEntity<>(additionalServiceService.getAdditionalService(id), HttpStatus.OK);
	} 
}
