package rs.ac.uns.ftn.xml.team17.adminservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.adminservice.model.roomCategory.RoomCategory;
import rs.ac.uns.ftn.xml.team17.adminservice.repository.RoomCategoryRepository;

@Service
public class RoomCategoryService {
	
	@Autowired
	private RoomCategoryRepository roomCategoryRepository;
	
	public RoomCategory getRoomCategory(Integer id) {
		Optional<RoomCategory> roomCategory = roomCategoryRepository.findById(id);
		if(!roomCategory.isPresent()) {
			//TODO: Ovde baciti exception
		}
		return roomCategory.get();
	}

	public RoomCategory save(RoomCategory roomCategory) {
		return roomCategoryRepository.save(roomCategory);
	}
	
	public Iterable<RoomCategory> findAll(){
		return roomCategoryRepository.findAll();
	}
	
	public Optional<RoomCategory> findRoomCategory(Integer id) {
		return roomCategoryRepository.findById(id);
	}

	/**
	 * Deactivates the existing room category.
	 * @param id - id of the selected room category
	 */
	public void deleteRoomCategory(Integer id) {
		Optional<RoomCategory> opt = findRoomCategory(id);
		
		if(!opt.isPresent()) {
			// TODO: exception
		}
		
		RoomCategory roomCategory = opt.get();
		if(!roomCategory.getActive()) {
			// TODO: exception
		}
		
		roomCategory.setActive(false);
		roomCategoryRepository.save(roomCategory);
	}
		
}
