package rs.ac.uns.ftn.xml.team17.authservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.ac.uns.ftn.xml.team17.authservice.dto.AuthenticationRequest;
import rs.ac.uns.ftn.xml.team17.authservice.service.auth.AuthenticationService;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationService authenticationService;
		
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest authenticationRequest){
		String token = authenticationService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword(), null);
		return ResponseEntity.ok(token);
	}
}
