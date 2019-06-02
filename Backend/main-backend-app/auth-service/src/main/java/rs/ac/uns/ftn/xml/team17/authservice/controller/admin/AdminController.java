package rs.ac.uns.ftn.xml.team17.authservice.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.authservice.dto.customer.CustomerDTO;
import rs.ac.uns.ftn.xml.team17.authservice.service.entityservice.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/block/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> blockCustomer(@PathVariable Integer id) {
		userService.blockCustomer(id);		
		return  new ResponseEntity<>("Customer is successfully blocked.", HttpStatus.OK);
	}

	@RequestMapping(value = "/activate/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> activateCustomer(@PathVariable Integer id) {
		userService.activateCustomer(id);
		return  new ResponseEntity<>("Customer is successfully activated.", HttpStatus.OK);

	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeCustomer(@PathVariable Integer id) {
		userService.removeCustomer(id);
		return  new ResponseEntity<>("Customer is successfully removed.", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getCustomers() {
		List<CustomerDTO> ret = userService.getCustomers();
		return new ResponseEntity<>(ret, HttpStatus.OK);	
	}


}
