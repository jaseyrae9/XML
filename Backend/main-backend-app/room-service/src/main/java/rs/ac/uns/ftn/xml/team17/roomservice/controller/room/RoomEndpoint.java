package rs.ac.uns.ftn.xml.team17.roomservice.controller.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.newroom.NewRoomRequest;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.newroom.NewRoomResponse;
import rs.ac.uns.ftn.xml.team17.roomservice.model.room.Room;
import rs.ac.uns.ftn.xml.team17.roomservice.service.RoomService;

@Endpoint
public class RoomEndpoint {

	@Autowired
	private RoomService roomService;
	
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/NewRoom", localPart = "newRoomRequest")
	@ResponsePayload
	public NewRoomResponse getHotel(@RequestPayload NewRoomRequest newRoomRequest){		
		NewRoomResponse response = new NewRoomResponse();
		Room r = roomService.addRoom(newRoomRequest);
		response.setRoomId(r.getId());
		response.setAddressId(r.getAddress().getId());
		return response;
	}

}
