package rs.ac.uns.ftn.xml.team17.authservice.service.entityservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.authservice.dto.customer.CustomerDTO;
import rs.ac.uns.ftn.xml.team17.authservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.authservice.exception.UserStatusChangeException;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.Agent;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.Customer;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.User;
import rs.ac.uns.ftn.xml.team17.authservice.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	private Customer findCustomer(Integer id) throws NotFoundException {
		Optional<User> userOpt = userRepository.findById(id);
		if(!userOpt.isPresent()) {
			throw new NotFoundException(id, Customer.class.getSimpleName());
		}
		User user = userOpt.get();
		if(!(user instanceof Customer)) {
			throw new NotFoundException(id, Customer.class.getSimpleName());
		}
		return (Customer) user;
	}

	/**
	 * Tries to find user with passed username. If no user is found causes
	 * UsernameNotFoundException.
	 * 
	 * @param username
	 * @throws UsernameNotFoundException
	 * @return
	 */
	public User findByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("Username " + username + " noy found.");
		}
		return user.get();
	}

	/**
	 * Saves user to datebase. Id is autogenerated.
	 * 
	 * @param user
	 * @return saved user with id set
	 */
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	/**
	 * For given username tries to find user and checks if user is hotel agent. If user is
	 * hotel agent returns hotel id, otherwise returns null.
	 * @param username
	 * @return
	 */
	public Integer getHotelId(String username) {
		Integer ret = null;
		Optional<User> userOpt = userRepository.findByUsername(username);
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			if(user instanceof Agent) {
				ret = ((Agent)user).getHotel().getId();
			}
		}
		return ret;
	}
	
	/**
	 * Blocks the existing customer. Checks if the user is a customer
	 * and has been previously blocked.
	 * 
	 * @param id - id of the customer
	 * @throws NotFoundException 
	 * @throws UserStatusChangeException 
	 */
	public User blockCustomer(Integer id) throws NotFoundException, UserStatusChangeException {
		Customer customer = this.findCustomer(id);		
		if(customer.getBlocked()) {
			throw new UserStatusChangeException(id);
		}
		customer.setBlocked(true);
		return userRepository.save(customer);
	}

	/**
	 * Activates the blocked customer. Checks if the user is a customer
	 * and has been previously blocked.
	 * 
	 * @param id - id of the customer
	 * @throws NotFoundException 
	 * @throws UserStatusChangeException 
	 */
	public User activateCustomer(Integer id) throws NotFoundException, UserStatusChangeException {
		Customer customer = this.findCustomer(id);		
		if(!customer.getBlocked()) {
			throw new UserStatusChangeException(id);
		}
		customer.setBlocked(false);
		return userRepository.save(customer);
	}

	/**
	 * Removes the active customer. Checks if the user is a customer.
	 * 
	 * @param id - id of the customer
	 * @throws NotFoundException 
	 */
	public User removeCustomer(Integer id) throws NotFoundException {
		Customer customer = this.findCustomer(id);
		customer.setActive(false);
		customer.setUsername(null);
		customer.setEmail(null);		
		return userRepository.save(customer);
	}
	
	/**
	 * Converts user to customer DTO.
	 * 
	 * @return informations about all customers
	 */
	public List<CustomerDTO> getCustomers() {
		Iterable<User> users = userRepository.findAll();
		
		// convert services to DTO
		List<CustomerDTO> ret = new ArrayList<>();
		for(User user: users) {
			if(user instanceof Customer) {
				ret.add(new CustomerDTO(user));
			}
		}
		return ret;		
	}
}
