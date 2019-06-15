package rs.ac.uns.ftn.xml.team17.reservationsservice.controller.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.GetMessagesRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.GetMessagesResponse;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newmessage.NewMessageRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newmessage.NewMessageResponse;
import rs.ac.uns.ftn.xml.team17.reservationsservice.service.MessageService;

@Endpoint
public class MessageEndpoint {

	@Autowired
	private MessageService messageService;
	
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/GetMessages", localPart = "getMessagesRequest")
    @ResponsePayload
    public GetMessagesResponse getMessages(@RequestPayload GetMessagesRequest getMessagesRequest) {
		System.out.println("MessageEndpoint getMessages");
		GetMessagesResponse response = new GetMessagesResponse();
		List<Message> ret = messageService.getMessages(getMessagesRequest.getDate());
		response.setMessage(ret);
        return response;
    }
	
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/NewMessage", localPart = "newMessageRequest")
    @ResponsePayload
    public NewMessageResponse newMessage(@RequestPayload NewMessageRequest newMessageRequest) {
		System.out.println("MessageEndpoint newMessage");
		NewMessageResponse response = new NewMessageResponse();
	    rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newmessage.Message m = messageService.newMessage(newMessageRequest);
		response.setMessage(m);
		return response;
    }
	
}
