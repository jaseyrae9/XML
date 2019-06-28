package rs.ac.uns.ftn.xml.team17.adminservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.adminservice.dto.soap.RoomType.GetRoomTypesResponse;
import rs.ac.uns.ftn.xml.team17.adminservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.adminservice.model.roomType.RoomType;
import rs.ac.uns.ftn.xml.team17.adminservice.repository.RoomTypeRepository;

@Service
public class RoomTypeService {

	@Autowired
	private RoomTypeRepository roomTypeRepository;

	public RoomType getRoomType(Integer id) throws NotFoundException {
		Optional<RoomType> roomType = roomTypeRepository.findById(id);
		if (!roomType.isPresent()) {
			throw new NotFoundException(id, RoomType.class.getSimpleName());
		}
		return roomType.get();
	}

	public RoomType save(RoomType roomType) {
		return roomTypeRepository.save(roomType);
	}

	public Iterable<RoomType> findAll() {
		return roomTypeRepository.findAll();
	}

	public Optional<RoomType> findRoomType(Integer id) {
		return roomTypeRepository.findById(id);
	}

	/**
	 * Deactivates the existing room type.
	 * 
	 * @param id - id of the selected room type
	 * @return
	 * @throws NotFoundException
	 */
	public RoomType deleteRoomType(Integer id) throws NotFoundException {
		RoomType roomType = this.getRoomType(id);
		roomType.setActive(false);
		roomTypeRepository.save(roomType);
		return roomType;
	}

	/**
	 * @return informations about all active room types.
	 */
	public Iterable<RoomType> getRoomTypes() {
		Iterable<RoomType> types = roomTypeRepository.findAll();
		return types;
	}

	public List<GetRoomTypesResponse.RoomType> getRoomTypesSoap() {
		Iterable<RoomType> types = roomTypeRepository.findAll();

		// convert services to DTO
		List<GetRoomTypesResponse.RoomType> ret = new ArrayList<>();
		for (RoomType type : types) {
			ret.add(new GetRoomTypesResponse.RoomType(type));
		}
		return ret;
	}

	/**
	 * Edits the existing room type.
	 * 
	 * @param roomTypeDTO - contains new informations for room type
	 * @return updated room type
	 * @throws NotFoundException 
	 */
	public RoomType editRoomType(Integer id, RoomType roomTypeDTO) throws NotFoundException {
		RoomType roomType = this.getRoomType(id);
		roomType.setName(roomTypeDTO.getName());
		return save(roomType);
	}

}
