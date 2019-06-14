package rs.ac.uns.ftn.xml.team17.reservationsservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.xml.team17.reservationsservice.model.message.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{ 
	
	List<Message> findAllByModificationDateAfter (Date date);

}
