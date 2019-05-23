package rs.ac.uns.ftn.xml.team17.authservice.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.authservice.model.user.User;
import rs.ac.uns.ftn.xml.team17.authservice.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Finds user with selected username.
	 * @param username
	 * @return
	 */
	public User findUser(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("Username " + username + " noy found.");
		}
		return user.get();
	}
}
