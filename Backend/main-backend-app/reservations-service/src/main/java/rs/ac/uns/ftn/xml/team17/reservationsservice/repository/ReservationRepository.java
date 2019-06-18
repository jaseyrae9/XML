package rs.ac.uns.ftn.xml.team17.reservationsservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	@Query("SELECT r FROM Reservation r WHERE r.modificationDate > :date AND r.room.hotel.id = :hotelId")
	public List<Reservation> findAll(@Param("date") Date date, @Param("hotelId") Integer hotelId);

}
