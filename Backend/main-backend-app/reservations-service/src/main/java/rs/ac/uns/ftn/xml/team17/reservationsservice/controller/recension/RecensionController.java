package rs.ac.uns.ftn.xml.team17.reservationsservice.controller.recension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension.RecensionDTO;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension.RecensionResponseDTO;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension.RoomRecensionDTO;

@RestController
@RequestMapping("/recension")
public class RecensionController {

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Gets all recensions for admin.
	 * 
	 * @return informations of all recensions.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<RecensionResponseDTO> getAllRecensions() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		List<RecensionResponseDTO> recensions = new ArrayList<RecensionResponseDTO>();
		HttpEntity<Object> entity = new HttpEntity<Object>(recensions, headers);
		return restTemplate.exchange("https://us-central1-xmlprojekat.cloudfunctions.net/app/recensions",
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<RecensionResponseDTO>>() {
				}).getBody();
	}

	/**
	 * Gets all approved recensions of the room with selected id for customer.
	 * 
	 * @param roomId - id of the room
	 * @return informations of approved recension of the selected room.
	 */
	@RequestMapping(value = "/approvedRecensions/{roomId}", method = RequestMethod.GET)
	public ResponseEntity<?> getRoomRecensions(@PathVariable Integer roomId) {
		// za korisnika
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		RoomRecensionDTO roomRecension = new RoomRecensionDTO();
		HttpEntity<RoomRecensionDTO> entity = new HttpEntity<RoomRecensionDTO>(roomRecension, headers);
		return new ResponseEntity<>(restTemplate
				.exchange("https://us-central1-xmlprojekat.cloudfunctions.net/app/approvedRecensions/" + roomId,
						HttpMethod.GET, entity, RoomRecensionDTO.class).getBody(), HttpStatus.OK);
	}

	/**
	 * Admin approves the selected recension.
	 * 
	 * @param recensionId - id of the selected recension
	 * @return
	 */
	@RequestMapping(value = "/{recensionId}", method = RequestMethod.PUT)
	public ResponseEntity<?> approveRecension(@PathVariable Integer recensionId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		
		RecensionResponseDTO recension = new RecensionResponseDTO();
		HttpEntity<RecensionResponseDTO> entity = new HttpEntity<RecensionResponseDTO>(recension, headers);
		
		return new ResponseEntity<>(restTemplate
				.exchange("https://us-central1-xmlprojekat.cloudfunctions.net/app/recensions/" + recensionId,
						HttpMethod.PUT, entity, RecensionResponseDTO.class)
				.getBody(), HttpStatus.OK);
	}

	/**
	 * Korisnik postavlja recenziju, koju admin treba da odobri.
	 * 
	 * @param recensionDTO - informations about the recension
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addRecension(@Valid @RequestBody RecensionDTO recensionDTO) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<RecensionDTO> entity = new HttpEntity<RecensionDTO>(recensionDTO, headers);
		return new ResponseEntity<>(restTemplate
				.exchange("https://us-central1-xmlprojekat.cloudfunctions.net/app/recensions",
						HttpMethod.POST, entity, RecensionResponseDTO.class)
				.getBody(), HttpStatus.OK);
	}
	
	

}
