package rs.ac.uns.ftn.xml.team17.searchservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.room.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	@Query("SELECT r FROM Room r "
			+ "WHERE "
			+ "NOT EXISTS( SELECT dr.id FROM DayReservation dr WHERE dr.reservation.room.id = r.id AND date BETWEEN :start AND :end) AND "
			+ "(:city IS NULL OR lower(r.address.city) LIKE lower(:city)) AND "
			+ "(r.numberOfPeople >= :numberOfPeople) AND "
			+ "(r.cancelationDays >= :cancelationDays OR :cancelationDays IS NULL) AND "
			+ "(r.type.id = :type OR :type IS NULL) AND "
			+ "(r.category.id = :category OR :category IS NULL) AND"
			+ "((:additionalServices) IS NULL OR"
			+ ":additionalServicesCount = (SELECT count(r2.id) FROM Room r2 INNER JOIN r2.additionalServices ras WHERE r2.id = r.id AND ras.id IN (:additionalServices) ))")
	public List<Room> searchRooms(Date start, Date end, String city, Integer numberOfPeople, 
									Integer type, Integer category, Integer cancelationDays, 
									List<Integer> additionalServices, Long additionalServicesCount);
	
}
