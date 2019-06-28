package rs.ac.uns.ftn.xml.team17.roomservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.xml.team17.roomservice.model.room.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	@Query("SELECT r FROM Room r WHERE r.id = :room and r.hotel.id = :hotel")
	public Optional<Room> getRoom(Integer room, Integer hotel);
}
