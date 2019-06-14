package rs.ac.uns.ftn.xml.team17.reservationsservice.controller.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getreservations.GetReservationsRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getreservations.GetReservationsResponse;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getreservations.Reservation;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newreservation.NewReservationRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newreservation.NewReservationResponse;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.reservation.ConfirmReservationRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.reservation.ConfirmReservationResponse;
import rs.ac.uns.ftn.xml.team17.reservationsservice.service.ReservationService;

@Endpoint
public class ReservationEndpoint {
								
	@Autowired
	private ReservationService reservationService;
	
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/Reservation", localPart = "confirmReservationRequest")
    @ResponsePayload
    public ConfirmReservationResponse confirmReservation(@RequestPayload ConfirmReservationRequest confirmReservationRequest) {
		ConfirmReservationResponse response = new ConfirmReservationResponse();
		reservationService.confirmReservation(confirmReservationRequest.getId());
		response.setSuccessfully(true);
        return response;
    }
	
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/GetReservations", localPart = "getReservationsRequest")
    @ResponsePayload
    public GetReservationsResponse getReservations(@RequestPayload GetReservationsRequest getReservationsRequest) {
		System.out.println("Get reservations controller");
		GetReservationsResponse response = new GetReservationsResponse();
		List<Reservation> ret = reservationService.getReservations(getReservationsRequest.getDate());
		response.setReservation(ret);
        return response;
    }
	
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/NewReservation", localPart = "newReservationRequest")
    @ResponsePayload
    public NewReservationResponse newReservation(@RequestPayload NewReservationRequest newReservationRequest) {
		System.out.println("New reservation controller");
		NewReservationResponse response = new NewReservationResponse();
		rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newreservation.Reservation r = new rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newreservation.Reservation(reservationService.newReservation(newReservationRequest));
        response.setReservation(r);
		return response;
    }
	
}
