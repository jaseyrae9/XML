package rs.ac.uns.ftn.xml.team17.authservice.controller.customer;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.authservice.dto.customer.CustomerDTO;
import rs.ac.uns.ftn.xml.team17.authservice.dto.customer.CustomerStatusChangeDTO;
import rs.ac.uns.ftn.xml.team17.authservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.authservice.exception.UserStatusChangeException;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.Customer;
import rs.ac.uns.ftn.xml.team17.authservice.service.entityservice.UserService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private UserService userService;

	/**
	 * If field blocked is false, method blocks customer. If field blocked is false customer is blocked.
	 * Changes the status of customer. If customer status is already blocked and DTO contains request for 
	 * customer status to be set to block, an exception will occur. If customer status is active and DTO
	 * contains request for customer status to be set to active, an exception will occur.
	 * 
	 * @param id - id of the customer
	 * @return 
	 * @throws UserStatusChangeException 
	 * @throws NotFoundException 
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> changeCustomerStatus(@PathVariable Integer id, @Valid @RequestBody CustomerStatusChangeDTO customerStatusChangeDTO) throws NotFoundException, UserStatusChangeException {
		Customer ret;
		if(!customerStatusChangeDTO.getBlocked()) {
			ret = (Customer) userService.activateCustomer(id);
		}
		else {
			ret = (Customer) userService.blockCustomer(id);	
		}
		return  new ResponseEntity<>(new CustomerDTO(ret), HttpStatus.OK);
	}
	
	/**
	 * Removes the existing customer with selected id.
	 * 
	 * @param id - id of the customer
	 * @return
	 * @throws NotFoundException 
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeCustomer(@PathVariable Integer id) throws NotFoundException {
		Customer customer = (Customer) userService.removeCustomer(id);
		return  new ResponseEntity<>(new CustomerDTO(customer), HttpStatus.OK);
	}

	/**
	 *  Returns DTO objects for customers. Objects contain username and if it is blocked.
	 *  
	 * @return information about all customers.
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getCustomers() {
		List<CustomerDTO> ret = userService.getCustomers();
		return new ResponseEntity<>(ret, HttpStatus.OK);	
	}


}
