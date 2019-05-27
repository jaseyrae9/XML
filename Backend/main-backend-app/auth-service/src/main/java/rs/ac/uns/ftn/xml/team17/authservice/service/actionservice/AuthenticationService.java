package rs.ac.uns.ftn.xml.team17.authservice.service.actionservice;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.authservice.dto.authentication.TokenValidationResponse;
import rs.ac.uns.ftn.xml.team17.authservice.model.temporary.CustomUserDetails;
import rs.ac.uns.ftn.xml.team17.authservice.security.DeviceProvider;
import rs.ac.uns.ftn.xml.team17.authservice.security.TokenUtils;

@Service
public class AuthenticationService {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	// UTILS for generating token
	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private DeviceProvider deviceProvider;

	public String login(String username, String password, HttpServletRequest request) {
		// check if user exists
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		// find device
		Device device = deviceProvider.getCurrentDevice(request);
		return this.tokenUtils.generateToken(customUserDetailsService.loadUserByUsername(username), device);
	}

	/**
	 * Checks if token is valid. If token is valid returns respose object with set
	 * username and list of authorites owned by user.
	 * 
	 * @param token
	 * @return object containting information about validity of token and token
	 *         owner
	 */
	public TokenValidationResponse validateToken(String token) {
		// create new response in which token is marked as invalid
		TokenValidationResponse response = new TokenValidationResponse();
		// remove Bearer keyword
		token = tokenUtils.getToken(token);
		if (token != null) {
			// find user linked to token
			String username = tokenUtils.getUsernameFromToken(token);
			if (username != null) {
				// load user and check if token is valid
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
				if (tokenUtils.validateToken(token, userDetails)) {
					// set information
					response.setIsValid(true);
					response.setUserId(((CustomUserDetails)userDetails).getId());
					response.setUsername(username);
					response.setAuthorities(StringUtils.join(userDetails.getAuthorities(), ','));
				}
			}
		}
		return response;
	}
}
