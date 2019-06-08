package rs.ac.uns.ftn.xml.team17.roomservice.controller.hotel;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.hotel.GetHotelRequest;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.hotel.GetHotelResponse;

@Endpoint
public class HotelEndpoint {

	//@Autowired
	//private HotelService hotelService;
	
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/hotel", localPart = "getHotelRequest")
	@ResponsePayload
	public GetHotelResponse getHotel(@RequestPayload GetHotelRequest getHotelRequest){			
        return null;
	}
	
}
