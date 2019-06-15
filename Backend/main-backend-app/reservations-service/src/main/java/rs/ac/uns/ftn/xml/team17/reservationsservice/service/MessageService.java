package rs.ac.uns.ftn.xml.team17.reservationsservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newmessage.NewMessageRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.message.Message;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.message.MessageDirection;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;
import rs.ac.uns.ftn.xml.team17.reservationsservice.repository.MessageRepository;
import rs.ac.uns.ftn.xml.team17.reservationsservice.repository.ReservationRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepostitory;

	@Autowired
	private ReservationRepository reservationRepository;

	public List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message> getMessages(Date date) {
		System.out.println("MessageService getMessages");
		System.out.println(date);

		List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message> ret = new ArrayList<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message>();

		List<Message> messages = messageRepostitory.findAllByModificationDateAfter(date);

		for (Message message : messages) {
			rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message m = new rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message(
					message);
			ret.add(m);
		}

		return ret;
	}

	public rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newmessage.Message newMessage(NewMessageRequest newMessageRequest) {
		Optional<Reservation> opt = reservationRepository.findById(newMessageRequest.getId());

		if (!opt.isPresent()) {
			// TODO: exception
		}

		Reservation r = opt.get();

		Message m = new Message(newMessageRequest.getMessage(), r);
		m.setStatus(MessageDirection.TO_CUSOTOMER);
		
		r.addMessage(m);
		
		reservationRepository.save(r);
		
		return new rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newmessage.Message(m);
	}
}
