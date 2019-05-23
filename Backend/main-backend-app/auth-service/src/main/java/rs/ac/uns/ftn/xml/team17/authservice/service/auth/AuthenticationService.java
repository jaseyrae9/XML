package rs.ac.uns.ftn.xml.team17.authservice.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.authservice.security.TokenUtils;

@Service
public class AuthenticationService {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	/**
	 * Generates JWT token if username and password match to user in database.
	 * @param username
	 * @param password
	 * @param device
	 * @return JWT token
	 */
	public String login(String username, String password, Device device) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));		
		return this.tokenUtils.generateToken(customUserDetailsService.loadUserByUsername(username), device);
	}
}
