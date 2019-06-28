package rs.ac.uns.ftn.xml.team17.roomservice.controller.room;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.addimage.AddImageRequest;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.addimage.AddImageResponse;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.newroom.NewRoomRequest;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.newroom.NewRoomResponse;
import rs.ac.uns.ftn.xml.team17.roomservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.roomservice.model.room.Room;
import rs.ac.uns.ftn.xml.team17.roomservice.service.RoomService;

@Endpoint
public class RoomEndpoint {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private HttpServletRequest request;
	
	@PreAuthorize("hasAnyRole('AGENT')")
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/NewRoom", localPart = "newRoomRequest")
	@ResponsePayload
	public NewRoomResponse addRoom(@RequestPayload NewRoomRequest newRoomRequest){		
		//TODO: izbaci oba id iz hotela
		NewRoomResponse response = new NewRoomResponse();
		Integer hotelId = Integer.parseInt(request.getHeader("Hotel"));
		Room r = roomService.addRoom(newRoomRequest, hotelId);
		response.setRoomId(r.getId());
		response.setAddressId(r.getAddress().getId());
		return response;
	}
	
	@PreAuthorize("hasAnyRole('AGENT')")
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/AddImage", localPart = "addImageRequest")
	@ResponsePayload
	public AddImageResponse addImage(@RequestPayload AddImageRequest addImageRequest) throws NotFoundException{		
		System.out.println("RoomEndpoint addImage");
		AddImageResponse response = new AddImageResponse();
		Integer hotelId = Integer.parseInt(request.getHeader("Hotel"));
		roomService.addImage(addImageRequest, hotelId);
		response.setSuccessfully(true);
		return response;
	}

}
