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
		
	// TODO: Logicko?
//	public void delete(RoomCategory roomCategory) {
//		roomCategoryRepository.delete(roomCategory);
//	}
}
