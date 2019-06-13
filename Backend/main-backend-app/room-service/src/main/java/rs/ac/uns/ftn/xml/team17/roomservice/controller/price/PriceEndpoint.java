package rs.ac.uns.ftn.xml.team17.roomservice.controller.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.price.SetPriceRequest;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.price.SetPriceResponse;
import rs.ac.uns.ftn.xml.team17.roomservice.service.RoomService;

@Endpoint
public class PriceEndpoint {

	@Autowired
	private RoomService roomService;
	
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/SetPrice", localPart = "setPriceRequest")
    @ResponsePayload
    public SetPriceResponse confirmReservation(@RequestPayload SetPriceRequest setPriceRequest) {
		System.out.println("Price endpoint");
		SetPriceResponse response = new SetPriceResponse();
		roomService.addPrice(setPriceRequest);
		response.setSuccessfully(true);
        return response;
    }
}
