package rs.ac.uns.ftn.xml.team17.authservice.controller.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.authservice.dto.authentication.AuthenticationRequest;
import rs.ac.uns.ftn.xml.team17.authservice.dto.authentication.AuthenticationResponse;
import rs.ac.uns.ftn.xml.team17.authservice.dto.authentication.TokenValidationResponse;
import rs.ac.uns.ftn.xml.team17.authservice.service.actionservice.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authenticationRequest, HttpServletRequest request){		
		String token = authenticationService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword(), request);
		AuthenticationResponse response = new AuthenticationResponse(token);
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "/validate-token")
	public ResponseEntity<TokenValidationResponse> validateToken(@RequestHeader(value="Authorization") String token){
		return ResponseEntity.ok(authenticationService.validateToken(token));
	}

}
