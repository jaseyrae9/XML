package rs.ac.uns.ftn.xml.team17.authservice.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.authservice.model.user.User;
import rs.ac.uns.ftn.xml.team17.authservice.security.CustomUserDetails;
import rs.ac.uns.ftn.xml.team17.authservice.service.user.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUser(username);
		return new CustomUserDetails(user);
	}

}
