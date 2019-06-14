package rs.ac.uns.ftn.xml.team17.reservationsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.xml.team17.reservationsservice.model.room.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

}
