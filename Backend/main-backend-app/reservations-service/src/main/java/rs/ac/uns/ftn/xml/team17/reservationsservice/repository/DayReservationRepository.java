package rs.ac.uns.ftn.xml.team17.reservationsservice.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.DayReservation;

public interface DayReservationRepository extends JpaRepository<DayReservation, Integer> {
	
	@Query("SELECT count(r) FROM DayReservation r WHERE r.reservation.room.id = :roomId and date BETWEEN :start AND :end")
	public Integer getAlreadyReservedDays(Integer roomId, Date start, Date end);

}
