package rs.ac.uns.ftn.xml.team17.authservice.service.actionservice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.authservice.security.DeviceProvider;
import rs.ac.uns.ftn.xml.team17.authservice.security.TokenUtils;

@Service
public class AuthenticationService {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;	
	@Autowired
	private AuthenticationManager authenticationManager;
	//UTILS for generating token
	@Autowired
	private TokenUtils tokenUtils;	
	@Autowired 
	private DeviceProvider deviceProvider;	

	public String login(String username, String password, HttpServletRequest request) {
		//check if user exists
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		//find device
		Device device = deviceProvider.getCurrentDevice(request);
		return this.tokenUtils.generateToken(customUserDetailsService.loadUserByUsername(username), device);
	}
}
