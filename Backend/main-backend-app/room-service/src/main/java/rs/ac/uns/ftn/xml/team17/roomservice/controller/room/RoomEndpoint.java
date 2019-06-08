package rs.ac.uns.ftn.xml.team17.roomservice.controller.room;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.room.NewRoomRequest;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.room.NewRoomResponse;

@Endpoint
public class RoomEndpoint {

	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/NewRoom", localPart = "newRoomRequest")
	@ResponsePayload
	public NewRoomResponse getHotel(@RequestPayload NewRoomRequest newRoomRequest){			
        return null;
	}
	
}
