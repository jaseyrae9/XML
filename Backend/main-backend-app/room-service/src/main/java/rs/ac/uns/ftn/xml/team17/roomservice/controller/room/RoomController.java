package rs.ac.uns.ftn.xml.team17.roomservice.controller.room;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.room.RoomFull;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/{roomId}")
	public ResponseEntity<RoomFull> getRoom(@PathVariable Integer roomId){
		return null;
	}
	
}
