package rs.ac.uns.ftn.xml.team17.reservationsservice.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension.RecensionDTO;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension.RecensionResponseDTO;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension.RoomRecensionDTO;
import rs.ac.uns.ftn.xml.team17.reservationsservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.reservationsservice.exception.RecensionException;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;

@Service
public class RecensionService {
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension> getRecensions(Date date, Integer hotelId) {
		System.out.println("RecensionService getRecensions");
		System.out.println(date.toString());
		
		List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension> ret = new ArrayList<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension>();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		List<RecensionResponseDTO> recensions = new ArrayList<RecensionResponseDTO>();
		HttpEntity<Object> entity = new HttpEntity<Object>(recensions, headers);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        String strDate = dateFormat.format(date);  
		strDate = strDate.replace(" ", "T");
		strDate += "Z";
		
		System.out.println("https://us-central1-xmlprojekat.cloudfunctions.net/app/recension/" + hotelId + "/" + strDate);
		List<RecensionResponseDTO> retRecensions = restTemplate.exchange("https://us-central1-xmlprojekat.cloudfunctions.net/app/recension/" + hotelId + "/" + strDate,
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<RecensionResponseDTO>>() {
				}).getBody();
	
		System.out.println("Povukao sve recenzije, sada ide konverzija");
		for(RecensionResponseDTO recension : retRecensions) {
			rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension r = new rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension(recension);
			ret.add(r);
		}
		
		System.out.println("Konvertovao, vracam rezultat");
		System.out.println(ret);
		return ret;
		
	}

	public List<RecensionResponseDTO> getAllRecensions() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		List<RecensionResponseDTO> recensions = new ArrayList<RecensionResponseDTO>();
		HttpEntity<Object> entity = new HttpEntity<Object>(recensions, headers);
		return restTemplate.exchange("https://us-central1-xmlprojekat.cloudfunctions.net/app/recension",HttpMethod.GET, entity, new ParameterizedTypeReference<List<RecensionResponseDTO>>() {
				}).getBody();
		
	}

	public RoomRecensionDTO getRoomRecensions(Integer roomId) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		RoomRecensionDTO roomRecension = new RoomRecensionDTO();
		HttpEntity<RoomRecensionDTO> entity = new HttpEntity<RoomRecensionDTO>(roomRecension, headers);
		return restTemplate.exchange("https://us-central1-xmlprojekat.cloudfunctions.net/app/approvedRecensions/" + roomId, HttpMethod.GET, entity, RoomRecensionDTO.class).getBody();
	}

	public Boolean approveRecension(String recensionId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		RecensionResponseDTO recension = new RecensionResponseDTO();
		HttpEntity<RecensionResponseDTO> entity = new HttpEntity<RecensionResponseDTO>(recension, headers);
		return restTemplate.exchange("https://us-central1-xmlprojekat.cloudfunctions.net/app/recension/" + recensionId,HttpMethod.PUT, entity, Boolean.class).getBody();
	}

	public RecensionResponseDTO addRecension(Integer customer, @Valid RecensionDTO recensionDTO) throws NotFoundException, RecensionException {
		Reservation reservation = this.reservationService.getReservation(recensionDTO.getReservationId(), customer);
		if(reservation.getStatus() != Reservation.ReservationStatus.HAPPENED) {
			throw new RecensionException(recensionDTO.getReservationId());
		}
		recensionDTO.setRoomId(reservation.getRoom().getId());
		recensionDTO.setHotelId(reservation.getRoom().getHotel().getId());
		
		reservation.setHasRecension(true);
		this.reservationService.saveReservation(reservation);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<RecensionDTO> entity = new HttpEntity<RecensionDTO>(recensionDTO, headers);
		return restTemplate.exchange("https://us-central1-xmlprojekat.cloudfunctions.net/app/recension",HttpMethod.POST, entity, RecensionResponseDTO.class).getBody();
	}

}
