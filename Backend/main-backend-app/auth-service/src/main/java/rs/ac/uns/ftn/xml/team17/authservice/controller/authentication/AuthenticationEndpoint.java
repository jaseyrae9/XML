package rs.ac.uns.ftn.xml.team17.authservice.controller.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.authservice.dto.authentication.AuthenticationRequest;
import rs.ac.uns.ftn.xml.team17.authservice.dto.authentication.AuthenticationResponse;
import rs.ac.uns.ftn.xml.team17.authservice.service.actionservice.AuthenticationService;

@Endpoint
public class AuthenticationEndpoint {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private HttpServletRequest request;
	
	@PayloadRoot(namespace="http://www.team17.xml.ftn.uns.ac.rs/authentication", localPart="authenticationRequest")
	@ResponsePayload
	public AuthenticationResponse login(@RequestPayload AuthenticationRequest authenticationRequest){			
		String token = authenticationService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword(), request);
		AuthenticationResponse response = new AuthenticationResponse(token);
		return response;
	}
	
}
