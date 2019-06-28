package rs.ac.uns.ftn.xml.team17.roomservice.controller.price;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.price.SetPriceRequest;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.price.SetPriceResponse;
import rs.ac.uns.ftn.xml.team17.roomservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.roomservice.service.RoomService;

@Endpoint
public class PriceEndpoint {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private HttpServletRequest request;
	
	@PreAuthorize("hasAnyRole('AGENT')")
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/SetPrice", localPart = "setPriceRequest")
    @ResponsePayload
    public SetPriceResponse confirmReservation(@RequestPayload SetPriceRequest setPriceRequest) throws NotFoundException {
		SetPriceResponse response = new SetPriceResponse();
		Integer hotelId = Integer.parseInt(request.getHeader("Hotel"));
		roomService.addPrice(setPriceRequest, hotelId);
		response.setSuccessfully(true);
        return response;
    }
}
