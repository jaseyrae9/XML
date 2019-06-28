package rs.ac.uns.ftn.xml.team17.reservationsservice.controller.reservation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.reservation.ReservationDTO;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.reservation.ReservationRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.reservationsservice.exception.ReservationImpossibleException;
import rs.ac.uns.ftn.xml.team17.reservationsservice.exception.ReservationStatusException;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;
import rs.ac.uns.ftn.xml.team17.reservationsservice.service.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ReservationDTO>> getReservations(@RequestHeader(value="UserId") Integer userId, Pageable pageable){
		return new ResponseEntity<Page<ReservationDTO>>(reservationService.getReservations(userId, pageable), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@RequestMapping(method = RequestMethod.GET, value = "/{reservationId}")
	public ResponseEntity<ReservationDTO> getReservation(@RequestHeader(value="UserId") Integer userId, @PathVariable Integer reservationId) throws NotFoundException{
		Reservation reservation = reservationService.getReservation(reservationId, userId);
		return new ResponseEntity<ReservationDTO>(new ReservationDTO(reservation), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ReservationDTO> createReservation(@RequestHeader(value="UserId") Integer customerId, @Valid @RequestBody ReservationRequest reservationRequest) throws ReservationImpossibleException, NotFoundException{
		Reservation reservation = reservationService.createReservation(reservationRequest, customerId);
		return new ResponseEntity<ReservationDTO>(new ReservationDTO(reservation), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{reservationId}")
	public ResponseEntity<ReservationDTO> cancelReservation(@RequestHeader(value="UserId") Integer customerId, @PathVariable Integer reservationId) throws NotFoundException, ReservationStatusException{
		Reservation reservation = reservationService.cancelReservation(customerId, reservationId);
		return new ResponseEntity<ReservationDTO>(new ReservationDTO(reservation), HttpStatus.OK);
	}
}
