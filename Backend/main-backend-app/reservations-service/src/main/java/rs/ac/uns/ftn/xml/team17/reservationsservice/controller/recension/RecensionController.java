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
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.recension.Recension;

@RestController
@RequestMapping("/recension")
public class RecensionController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Gets all recensions.
	 * @return informations of all recensions.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Recension> getAllRecensions() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		List<Recension> recensions = new ArrayList<Recension>();
		HttpEntity<Object> entity = new HttpEntity<Object>(recensions, headers);
		return restTemplate.exchange("https://us-central1-xmlprojekat.cloudfunctions.net/app/recensions", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Recension>>() {}).getBody();
	}
	
	/**
	 * Gets all recensions of the reservation with selected id.
	 * 
	 * @param recensionId - id of the reservation
	 * @return
	 */
	@RequestMapping(value = "/{reservationId}", method = RequestMethod.GET)
	public  ResponseEntity<?> getReservationRecension(@PathVariable Integer recensionId) {
		// za admina da moze da vidi recenziju, pa da je odobri
		return null;
	}

	/**
	 * Gets all approved recensions of the room with selected id. 
	 * 
	 * @param roomId - id of the room
	 * @return informations of approved recension of the selected room.
	 */
	@RequestMapping(value = "/{roomId}", method = RequestMethod.GET) 
	public  ResponseEntity<?> getRoomRecensions(@PathVariable Integer roomId) {
		// za korisnika
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		List<Recension> recensions = new ArrayList<Recension>();
		HttpEntity<Object> entity = new HttpEntity<Object>(recensions, headers);
		return new ResponseEntity<>(restTemplate.exchange("https://us-central1-xmlprojekat.cloudfunctions.net/app/approvedRecensions/" + roomId, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Recension>>() {}).getBody(), HttpStatus.OK);
	}
	
	/**
	 * Approves the selected recension.
	 * @param recensionId - id of the selected recension
	 * @return
	 */
	@RequestMapping(value = "/{recensionId}", method = RequestMethod.PUT)
	public ResponseEntity<?> approveRecension(@PathVariable Integer recensionId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		Recension recension = new Recension();
		HttpEntity<Recension> entity = new HttpEntity<Recension>(recension, headers);
		return new ResponseEntity<>(restTemplate.exchange("https://us-central1-xmlprojekat.cloudfunctions.net/app/recensions/" + recensionId, HttpMethod.PUT, entity, Recension.class).getBody(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addRecension(@Valid @RequestBody RecensionDTO recensionDTO) {
		
		// TODO: Pogoditi klaud i postaviti recenziju
		
		return null;
	}
	
	
	



}
