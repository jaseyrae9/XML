package rs.ac.uns.ftn.xml.team17.reservationsservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.reservationsservice.model.room.Room;
import rs.ac.uns.ftn.xml.team17.reservationsservice.repository.RoomRepository;

@Service
public class RoomService {
	@Autowired
	private RoomRepository roomRepository;
	
	/**
	 * Finds room with given id.
	 * @param id
	 * @return
	 */
	public Room getRoom(Integer id) {
		Optional<Room> opt = roomRepository.findById(id);
		if (!opt.isPresent()) {
			// TODO: exception
		}
		return opt.get();
	}
}
