package rs.ac.uns.ftn.xml.team17.authservice.controller.registration;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.authservice.dto.registration.AgentRegistrationDTO;
import rs.ac.uns.ftn.xml.team17.authservice.dto.registration.CustomerRegistrationDTO;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.User;
import rs.ac.uns.ftn.xml.team17.authservice.service.actionservice.RegistrationService;

@RestController
public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> registerCustomer(@RequestBody @Valid CustomerRegistrationDTO customerRegistrationDTO){		
		User user = registrationService.registerCustomer(customerRegistrationDTO);
		return ResponseEntity.ok(user);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/register/agent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> registerAgent(@RequestBody @Valid AgentRegistrationDTO agentRegistrationDTO) {	
		User user = registrationService.registerAgent(agentRegistrationDTO);
		return ResponseEntity.ok(user);
	}

}
