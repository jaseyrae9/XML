package rs.ac.uns.ftn.xml.team17.reservationsservice.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.reservation.ReservationDTO;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	@Query("SELECT r FROM Reservation r WHERE r.modificationDate > :date AND r.room.hotel.id = :hotelId")
	public List<Reservation> findAll(@Param("date") Date date, @Param("hotelId") Integer hotelId);
	
	@Query("SELECT r FROM Reservation r WHERE r.id = :id AND r.customer.id = :customer")
	public Optional<Reservation> findByCustomerAndId(Integer id, Integer customer);
	
	@Query("SELECT r FROM Reservation r WHERE r.customer.id = :customer")
	public Page<ReservationDTO> findCustomerReservation(Integer customer, Pageable pageable);
	
	@Query("SELECT r FROM Reservation r WHERE r.id = :id and r.room.hotel.id = :hotel")
	public Optional<Reservation> findAgentReservation(Integer id, Integer hotel);
}
