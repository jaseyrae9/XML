package rs.ac.uns.ftn.xml.team17.authservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.authservice.dto.AuthenticationRequestDTO;
import rs.ac.uns.ftn.xml.team17.authservice.service.actionservice.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
	@Autowired
	private AuthenticationService authenticationService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody @Valid AuthenticationRequestDTO authenticationRequestDTO, HttpServletRequest request){		
		String token = authenticationService.login(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword(), request);
		return ResponseEntity.ok(token);
	}

}
