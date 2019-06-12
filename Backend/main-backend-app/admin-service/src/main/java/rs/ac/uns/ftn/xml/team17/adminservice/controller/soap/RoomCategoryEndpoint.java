package rs.ac.uns.ftn.xml.team17.adminservice.controller.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.adminservice.dto.soap.RoomCategory.GetRoomCategoriesRequest;
import rs.ac.uns.ftn.xml.team17.adminservice.dto.soap.RoomCategory.GetRoomCategoriesResponse;
import rs.ac.uns.ftn.xml.team17.adminservice.service.RoomCategoryService;

@Endpoint
public class RoomCategoryEndpoint {

	@Autowired
	private RoomCategoryService roomCategoryService;
	
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/roomCategory", localPart = "getRoomCategoriesRequest")
	@ResponsePayload
	public GetRoomCategoriesResponse getRoomTypes(@RequestPayload GetRoomCategoriesRequest getRoomCategoriesRequest){		
		GetRoomCategoriesResponse response = new GetRoomCategoriesResponse();
		List<GetRoomCategoriesResponse.RoomCategory> ret = roomCategoryService.getRoomCategoriesSoap();
		response.setRoomCategory(ret);
        return response;
	}
}
