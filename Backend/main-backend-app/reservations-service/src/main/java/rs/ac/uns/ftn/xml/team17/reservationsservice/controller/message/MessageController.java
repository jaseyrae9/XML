package rs.ac.uns.ftn.xml.team17.reservationsservice.controller.message;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.message.Message;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.message.MessageRequest;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Message> sendMessage(@RequestHeader(value="UserId") Integer userId, @Valid @RequestBody MessageRequest messageRequest){
		return null;
	}
}
