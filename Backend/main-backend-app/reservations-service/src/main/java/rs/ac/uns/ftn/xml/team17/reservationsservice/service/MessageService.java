package rs.ac.uns.ftn.xml.team17.reservationsservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.reservationsservice.model.message.Message;
import rs.ac.uns.ftn.xml.team17.reservationsservice.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepostitory;
	
	public List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message> getMessages(Date date) {
		System.out.println("MessageService getMessages");
		System.out.println(date);
		
		List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message> ret = new ArrayList<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message>();
		
		List<Message> messages = messageRepostitory.findAllByModificationDateAfter(date);
		
		for(Message message : messages) {
			rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message m = new rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message(message);
			ret.add(m);
		}
		
		return ret;
	}
}
