package rs.ac.uns.ftn.xml.team17.reservationsservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	List<Reservation> findAllByModificationDateAfter (Date date);

}
