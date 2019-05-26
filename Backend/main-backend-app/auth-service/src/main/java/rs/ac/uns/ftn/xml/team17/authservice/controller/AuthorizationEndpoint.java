package rs.ac.uns.ftn.xml.team17.authservice.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.authservice.service.actionservice.AuthenticationService;
import rs.ac.uns.ftn.xml.team17.authservice.soap.AuthentificationRequest;
import rs.ac.uns.ftn.xml.team17.authservice.soap.AuthentificationResponse;

@Endpoint
public class AuthorizationEndpoint {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private HttpServletRequest request;
	
	@PayloadRoot(namespace="http://www.team17.xml.ftn.uns.ac.rs/Authentification", localPart="authentificationRequest")
	@ResponsePayload
	public AuthentificationResponse login(@RequestPayload AuthentificationRequest authenticationRequest){		
		System.out.println("Soap login");
		if(request == null ) {
			System.out.println("Request is null, login 2");
		}
		String token = authenticationService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword(), request);
		AuthentificationResponse response = new AuthentificationResponse();
		response.setToken(token);
		System.out.println("Vraca token");
		return response;
	}
	
}
