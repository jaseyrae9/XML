package rs.ac.uns.ftn.xml.team17.reservationsservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.message.MessageDTO;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.message.MessageRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newmessage.NewMessageRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.message.Message;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.message.MessageDirection;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;
import rs.ac.uns.ftn.xml.team17.reservationsservice.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepostitory;

	@Autowired
	private ReservationService reservationService;
	
	public Message save(Message message) {
		return messageRepostitory.save(message);
	}
	
	private Message createMessage(Reservation reservation, String text) {
		Message message = new Message(text, reservation);
		return message;
	}
	
	/**
	 * Creates new message from current user.
	 * @param messageRequest - reservation id and message text
	 * @param customer - id of customer
	 * @return message
	 * @throws NotFoundException 
	 */
	public Message createMessage(MessageRequest messageRequest, Integer customer, Integer reservation) throws NotFoundException {
		Reservation r = reservationService.getReservation(reservation, customer);
		Message m = this.createMessage(r, messageRequest.getMessage());
		m.setStatus(MessageDirection.TO_AGENT);	
		return this.save(m);		
	}
	
	/**
	 * Returns all messages where customer and reservation it match given.
	 * @param customer
	 * @param reservation
	 * @param pageable
	 * @return
	 */
	public Page<MessageDTO> getMessages(Integer customer, Integer reservation, Pageable pageable){
		return messageRepostitory.getByReservationAndCustomer(reservation, customer, pageable);
	}
	
	public rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newmessage.Message newMessage(NewMessageRequest newMessageRequest) throws NotFoundException {
		//TODO: Proveri sme li ovaj agent za ove rezervacije da salje
		Reservation r = reservationService.getReservation(newMessageRequest.getId());
		Message m = this.createMessage(r, newMessageRequest.getMessage());
		m.setStatus(MessageDirection.TO_CUSTOMER);			
		return new rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newmessage.Message(this.save(m));
	}

	public List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message> getMessages(Date date, Integer hotelId) {
		System.out.println("MessageService getMessages");
		System.out.println(date);

		List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message> ret = new ArrayList<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message>();

		List<Message> messages = messageRepostitory.findAll(date, hotelId);

		for (Message message : messages) {
			rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message m = new rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getmessages.Message(
					message);
			ret.add(m);
		}

		return ret;
	}

}
