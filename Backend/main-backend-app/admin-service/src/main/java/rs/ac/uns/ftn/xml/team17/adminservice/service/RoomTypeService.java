package rs.ac.uns.ftn.xml.team17.adminservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.adminservice.dto.RoomTypeDTO;
import rs.ac.uns.ftn.xml.team17.adminservice.dto.soap.RoomType.GetRoomTypesResponse;
import rs.ac.uns.ftn.xml.team17.adminservice.model.roomType.RoomType;
import rs.ac.uns.ftn.xml.team17.adminservice.repository.RoomTypeRepository;

@Service
public class RoomTypeService {

	@Autowired
	private RoomTypeRepository roomTypeRepository;

	public RoomType getRoomType(Integer id) {
		Optional<RoomType> roomType = roomTypeRepository.findById(id);
		if (!roomType.isPresent()) {
			// TODO: Ovde baciti exception
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
	 */
	public void deleteRoomType(Integer id) {
		Optional<RoomType> opt = findRoomType(id);

		if (!opt.isPresent()) {
			// TODO: exception
		}

		RoomType roomType = opt.get();
		if (!roomType.getActive()) {
			// TODO: exception
		}

		roomType.setActive(false);
		roomTypeRepository.save(roomType);
	}

	/**
	 * Converts room types to DTO.
	 * 
	 * @return informations about all room types.
	 */
	public List<RoomTypeDTO> getRoomTypes() {
		Iterable<RoomType> types = roomTypeRepository.findAll();

		// convert services to DTO
		List<RoomTypeDTO> ret = new ArrayList<>();
		for (RoomType type : types) {
			ret.add(new RoomTypeDTO(type));
		}
		return ret;
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
	 */
	public RoomType editRoomType(@Valid RoomTypeDTO roomTypeDTO) {
		Optional<RoomType> opt = roomTypeRepository.findById(roomTypeDTO.getId());
		if (!opt.isPresent()) {
			// TODO: exception
		}

		// set name and description
		opt.ifPresent(roomType -> {
			roomType.setName(roomTypeDTO.getName());
		});
		return roomTypeRepository.save(opt.get());
	}

}
