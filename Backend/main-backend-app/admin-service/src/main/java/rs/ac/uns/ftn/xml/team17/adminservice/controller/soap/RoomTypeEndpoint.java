package rs.ac.uns.ftn.xml.team17.adminservice.controller.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.adminservice.dto.soap.RoomType.GetRoomTypesRequest;
import rs.ac.uns.ftn.xml.team17.adminservice.dto.soap.RoomType.GetRoomTypesResponse;
import rs.ac.uns.ftn.xml.team17.adminservice.dto.soap.RoomType.GetRoomTypesResponse.RoomType;
import rs.ac.uns.ftn.xml.team17.adminservice.service.RoomTypeService;

@Endpoint
public class RoomTypeEndpoint {

	@Autowired
	private RoomTypeService roomTypeService;
	
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/roomType", localPart = "getRoomTypesRequest")
	@ResponsePayload
	public GetRoomTypesResponse getRoomTypes(@RequestPayload GetRoomTypesRequest getRoomTypesRequest){			
		GetRoomTypesResponse response = new GetRoomTypesResponse();
		List<RoomType> ret = roomTypeService.getRoomTypesSoap();
		response.setRoomType(ret);
        return response;
	}
}
