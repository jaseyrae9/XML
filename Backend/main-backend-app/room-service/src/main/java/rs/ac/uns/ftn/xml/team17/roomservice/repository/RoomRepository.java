package rs.ac.uns.ftn.xml.team17.roomservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.xml.team17.roomservice.model.room.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

}
