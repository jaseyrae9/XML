package rs.ac.uns.ftn.xml.team17.authservice.service.actionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.authservice.dto.registration.CustomerRegistrationDTO;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.Authority;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.User;
import rs.ac.uns.ftn.xml.team17.authservice.service.entityservice.AuthorityService;
import rs.ac.uns.ftn.xml.team17.authservice.service.entityservice.UserService;

@Service
public class RegistrationService {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * Registers new customer. Encrypts password. Adds role CUSTOMER to user.
	 * @param customerRegistrationDTO registration data
	 * @return customer with set id
	 */
	public User registerCustomer(CustomerRegistrationDTO customerRegistrationDTO) {
		User customer = customerRegistrationDTO.createCustomer();
		return registerUser(customer, customerRegistrationDTO.getPassword(), "CUSTOMER");
	}
	
	private User registerUser(User user, String password, String role) {
		//postavi kriptovanu sifru
		user.setPassword(passwordEncoder.encode(password));
		//dodavanje uloge
		Authority authority = authorityService.findByName(role);
		user.getUserAuthorities().add(authority);
		return userService.saveUser(user);
	}
}
