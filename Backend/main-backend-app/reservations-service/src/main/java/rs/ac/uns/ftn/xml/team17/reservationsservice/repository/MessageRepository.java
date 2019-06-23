package rs.ac.uns.ftn.xml.team17.reservationsservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.message.MessageDTO;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.message.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
	
	@Query("SELECT m FROM Message m WHERE m.modificationDate > :date AND m.reservation.room.hotel.id = :hotelId")
	List<Message> findAll(@Param("date") Date date, @Param("hotelId") Integer hotelId);

	@Query("SELECT m FROM Message m WHERE m.reservation.id = :reservation AND m.reservation.customer.id = :customer")
	Page<MessageDTO> getByReservationAndCustomer(Integer reservation, Integer customer, Pageable pageable);
}
