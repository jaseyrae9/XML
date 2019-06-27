package rs.ac.uns.ftn.xml.team17.reservationsservice.controller.recension;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension.RecensionDTO;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension.RecensionResponseDTO;
import rs.ac.uns.ftn.xml.team17.reservationsservice.service.RecensionService;

@RestController
@RequestMapping("/recension")
public class RecensionController {

	@Autowired
	private RecensionService recensionService;
	
	/**
	 * Gets all recensions for admin.
	 * 
	 * @return informations of all recensions.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<RecensionResponseDTO> getAllRecensions() {
		return recensionService.getAllRecensions();
	}

	/**
	 * Gets all approved recensions of the room with selected id for customer.
	 * 
	 * @param roomId - id of the room
	 * @return informations of approved recension of the selected room.
	 */
	@RequestMapping(value = "/approvedRecensions/{roomId}", method = RequestMethod.GET)
	public ResponseEntity<?> getRoomRecensions(@PathVariable Integer roomId) {
		return new ResponseEntity<>(recensionService.getRoomRecensions(roomId), HttpStatus.OK);
	}

	/**
	 * Admin approves the selected recension.
	 * 
	 * @param recensionId - id of the selected recension
	 * @return
	 */
	@RequestMapping(value = "/{recensionId}", method = RequestMethod.PUT)
	public ResponseEntity<?> approveRecension(@PathVariable String recensionId) {
		return new ResponseEntity<>(recensionService.approveRecension(recensionId), HttpStatus.OK);
	}

	/**
	 * Korisnik postavlja recenziju, koju admin treba da odobri.
	 * 
	 * @param recensionDTO - informations about the recension
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addRecension(@Valid @RequestBody RecensionDTO recensionDTO) {
		return new ResponseEntity<>(recensionService.addRecension(recensionDTO), HttpStatus.OK);
	}
	
	

}
