package rs.ac.uns.ftn.xml.team17.reservationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.reservationservice.dto.reservations.GetReservationsRequest;
import rs.ac.uns.ftn.xml.team17.reservationservice.dto.reservations.GetReservationsResponse;
import rs.ac.uns.ftn.xml.team17.reservationservice.service.ReservationService;

@Endpoint
public class ReservationEndpoint {

	@Autowired
	private ReservationService reservationService;
	
	@PayloadRoot(namespace="http://www.team17.xml.ftn.uns.ac.rs/reservation", localPart="getReservationsRequest")
	@ResponsePayload
	public GetReservationsResponse login(@RequestPayload GetReservationsRequest request){			
		// TODO: poslovna logika
		return null;
	}
	
}
