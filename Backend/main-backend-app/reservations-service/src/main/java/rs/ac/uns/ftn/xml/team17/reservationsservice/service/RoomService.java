package rs.ac.uns.ftn.xml.team17.reservationsservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.reservationsservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.room.Room;
import rs.ac.uns.ftn.xml.team17.reservationsservice.repository.RoomRepository;

@Service
public class RoomService {
	@Autowired
	private RoomRepository roomRepository;
	
	public Room getAgentRoom(Integer id, Integer hotelId) throws NotFoundException {
		Optional<Room> opt = roomRepository.findAgentRoom(id, hotelId);
		if (!opt.isPresent()) {
			throw new NotFoundException(id, Room.class.getSimpleName());
		}
		return opt.get();
	}
	
	/**
	 * Finds room with given id.
	 * @param id
	 * @return
	 * @throws NotFoundException 
	 */
	public Room getRoom(Integer id) throws NotFoundException {
		Optional<Room> opt = roomRepository.findById(id);
		if (!opt.isPresent()) {
			throw new NotFoundException(id, Room.class.getSimpleName());
		}
		return opt.get();
	}
}
