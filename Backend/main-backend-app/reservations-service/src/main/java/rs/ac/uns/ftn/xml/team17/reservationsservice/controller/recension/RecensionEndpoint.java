package rs.ac.uns.ftn.xml.team17.reservationsservice.controller.recension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.GetRecensionsRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.GetRecensionsResponse;
import rs.ac.uns.ftn.xml.team17.reservationsservice.service.RecensionService;

@Endpoint
public class RecensionEndpoint {
	
	@Autowired
	private RecensionService recensionService;
	
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/GetRecensions", localPart = "getRecensionsRequest")
    @ResponsePayload
    public GetRecensionsResponse getRecensions(@RequestPayload GetRecensionsRequest getRecensionsRequest) {
		GetRecensionsResponse response = new GetRecensionsResponse();
		recensionService.getRecensions(getRecensionsRequest.getDate());
		//reservationService.confirmReservation(confirmReservationRequest.getId());
        return response;
    }

}
