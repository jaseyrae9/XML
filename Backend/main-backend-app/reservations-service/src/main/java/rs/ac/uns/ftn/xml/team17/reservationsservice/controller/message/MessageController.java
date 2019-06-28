package rs.ac.uns.ftn.xml.team17.reservationsservice.controller.message;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.message.MessageDTO;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.message.MessageRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.message.Message;
import rs.ac.uns.ftn.xml.team17.reservationsservice.service.MessageService;

@RestController
@RequestMapping("/reservation/{reservationId}")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@RequestMapping(value = "/message", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageDTO> sendMessage(@RequestHeader(value="UserId") Integer customer, @PathVariable Integer reservationId, @Valid @RequestBody MessageRequest messageRequest) throws NotFoundException{
		Message m = this.messageService.createMessage(messageRequest, customer, reservationId);
		return new ResponseEntity<MessageDTO>(new MessageDTO(m), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public ResponseEntity<Page<MessageDTO>> getMessages(@RequestHeader(value="UserId") Integer customer, @PathVariable Integer reservationId, Pageable pageable){
		return new ResponseEntity<Page<MessageDTO>>(this.messageService.getMessages(customer, reservationId, pageable), HttpStatus.OK);
	}
}
