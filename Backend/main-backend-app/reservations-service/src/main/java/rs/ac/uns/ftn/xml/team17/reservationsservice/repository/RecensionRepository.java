package rs.ac.uns.ftn.xml.team17.reservationsservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rs.ac.uns.ftn.xml.team17.reservationsservice.model.recension.Recension;

public interface RecensionRepository  extends JpaRepository<Recension, Integer>  {

	@Query("SELECT r FROM Recension r WHERE r.modificationDate > :date AND r.reservation.room.hotel.id = :hotelId")
	List<Recension> findAll(@Param("date") Date date, @Param("hotelId") Integer hotelId);
}
