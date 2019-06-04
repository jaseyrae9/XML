package rs.ac.uns.ftn.xml.team17.adminservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.adminservice.dto.RoomCategoryDTO;
import rs.ac.uns.ftn.xml.team17.adminservice.model.roomCategory.RoomCategory;
import rs.ac.uns.ftn.xml.team17.adminservice.repository.RoomCategoryRepository;

@Service
public class RoomCategoryService {

	@Autowired
	private RoomCategoryRepository roomCategoryRepository;

	public RoomCategory getRoomCategory(Integer id) {
		Optional<RoomCategory> roomCategory = roomCategoryRepository.findById(id);
		if (!roomCategory.isPresent()) {
			// TODO: Ovde baciti exception
		}
		return roomCategory.get();
	}

	public RoomCategory save(RoomCategory roomCategory) {
		return roomCategoryRepository.save(roomCategory);
	}

	public Iterable<RoomCategory> findAll() {
		return roomCategoryRepository.findAll();
	}

	public Optional<RoomCategory> findRoomCategory(Integer id) {
		return roomCategoryRepository.findById(id);
	}

	/**
	 * Deactivates the existing room category.
	 * 
	 * @param id - id of the selected room category
	 */
	public void deleteRoomCategory(Integer id) {
		Optional<RoomCategory> opt = findRoomCategory(id);

		if (!opt.isPresent()) {
			// TODO: exception
		}

		RoomCategory roomCategory = opt.get();
		if (!roomCategory.getActive()) {
			// TODO: exception
		}

		roomCategory.setActive(false);
		roomCategoryRepository.save(roomCategory);
	}

	/**
	 * Converts room categories to DTO.
	 * 
	 * @return informations about all room categories.
	 */
	public List<RoomCategoryDTO> getRoomCategories() {
		Iterable<RoomCategory> categories = roomCategoryRepository.findAll();

		// convert categories to DTO
		List<RoomCategoryDTO> ret = new ArrayList<>();
		for (RoomCategory category : categories) {
			ret.add(new RoomCategoryDTO(category));
		}
		return ret;
	}

	/**
	 * Edits the existing room category.
	 * 
	 * @param roomCategoryDTO - contains new informations for room category
	 * @return updated room category
	 */
	public RoomCategory editRoomCategory(@Valid RoomCategoryDTO roomCategoryDTO) {
		Optional<RoomCategory> opt = roomCategoryRepository.findById(roomCategoryDTO.getId());
		if (!opt.isPresent()) {
			// TODO: exception
		}

		// set number of stars and description
		opt.ifPresent(roomCategory -> {
			roomCategory.setNumberOfStars(roomCategoryDTO.getNumberOfStars());
			roomCategory.setDescription(roomCategoryDTO.getDescription());
		});

		return roomCategoryRepository.save(opt.get());
	}

}
