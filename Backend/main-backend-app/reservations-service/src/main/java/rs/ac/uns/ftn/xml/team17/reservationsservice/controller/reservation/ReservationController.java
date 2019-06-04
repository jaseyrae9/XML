package rs.ac.uns.ftn.xml.team17.reservationsservice.controller.reservation;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.reservation.FullReservation;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.reservation.ReservationPreview;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.reservation.ReservationRequest;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
	
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ReservationPreview>> getReservations(@RequestHeader(value="UserId") Integer userId, Pageable page){
		return null;
	}
	
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@RequestMapping(method = RequestMethod.GET, value = "/{reservationId}")
	public ResponseEntity<FullReservation> getReservation(@RequestHeader(value="UserId") Integer userId, @PathVariable Integer reservationId){
		return null;
	}
	
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<FullReservation> createReservation(@RequestHeader(value="UserId") Integer userId, @Valid @RequestBody ReservationRequest reservationRequest){
		return null;
	}
	
	@PreAuthorize("hasAnyRole('CUSTOMER')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{reservationId}")
	public ResponseEntity<FullReservation> cancelReservation(@RequestHeader(value="UserId") Integer userId, @PathVariable Integer reservationId){
		return null;
	}
}
