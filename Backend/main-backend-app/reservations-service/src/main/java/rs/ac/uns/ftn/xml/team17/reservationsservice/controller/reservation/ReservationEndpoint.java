package rs.ac.uns.ftn.xml.team17.reservationsservice.controller.reservation;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.reservation.ConfirmReservationRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.reservation.ConfirmReservationResponse;

@Endpoint
public class ReservationEndpoint {
								
	// TODO: dodati u config
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/Reservation", localPart = "confirmReservationRequest")
    @ResponsePayload
    public ConfirmReservationResponse confirmReservation(@RequestPayload ConfirmReservationRequest confirmReservationRequest) {
		ConfirmReservationResponse response = new ConfirmReservationResponse();

        return response;
    }
	
}
