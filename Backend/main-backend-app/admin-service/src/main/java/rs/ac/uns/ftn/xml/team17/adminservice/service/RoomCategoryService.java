package rs.ac.uns.ftn.xml.team17.adminservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.adminservice.dto.soap.RoomCategory.GetRoomCategoriesResponse;
import rs.ac.uns.ftn.xml.team17.adminservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.adminservice.model.roomCategory.RoomCategory;
import rs.ac.uns.ftn.xml.team17.adminservice.repository.RoomCategoryRepository;

@Service
public class RoomCategoryService {

	@Autowired
	private RoomCategoryRepository roomCategoryRepository;

	public RoomCategory getRoomCategory(Integer id) throws NotFoundException {
		Optional<RoomCategory> roomCategory = roomCategoryRepository.findById(id);
		if (!roomCategory.isPresent()) {
			throw new NotFoundException(id, RoomCategory.class.getSimpleName());
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
	 * @return
	 * @throws NotFoundException
	 */
	public RoomCategory deleteRoomCategory(Integer id) throws NotFoundException {
		RoomCategory roomCategory = this.getRoomCategory(id);
		roomCategory.setActive(false);
		roomCategoryRepository.save(roomCategory);
		return roomCategory;
	}

	/**
	 * @return informations about all active room categories.
	 */
	public Iterable<RoomCategory> getRoomCategories() {
		Iterable<RoomCategory> categories = roomCategoryRepository.findAll();
		return categories;
	}

	public List<GetRoomCategoriesResponse.RoomCategory> getRoomCategoriesSoap() {
		Iterable<RoomCategory> categories = roomCategoryRepository.findAll();

		// convert categories to DTO
		List<GetRoomCategoriesResponse.RoomCategory> ret = new ArrayList<>();
		for (RoomCategory category : categories) {
			ret.add(new GetRoomCategoriesResponse.RoomCategory(category));
		}
		return ret;
	}

	/**
	 * Edits the existing room category.
	 * 
	 * @param roomCategoryDTO - contains new informations for room category
	 * @return updated room category
	 * @throws NotFoundException 
	 */
	public RoomCategory editRoomCategory(Integer id, RoomCategory roomCategoryDTO) throws NotFoundException {
		RoomCategory roomCategory = this.getRoomCategory(id);
		roomCategory.setNumberOfStars(roomCategoryDTO.getNumberOfStars());
		roomCategory.setDescription(roomCategoryDTO.getDescription());
		return save(roomCategory);
	}

}
