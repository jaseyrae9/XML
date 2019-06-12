package rs.ac.uns.ftn.xml.team17.roomservice.controller.room;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.date.DateRange;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.room.RoomFull;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/{roomId}")
	public ResponseEntity<RoomFull> getRoom(@PathVariable Integer roomId){
		System.out.println("U metodi getRoom");
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "price/{roomId}")
	public ResponseEntity<?> getRoomPrice(@PathVariable Integer roomId, @RequestBody DateRange dateRange) {
		System.out.println("U metodi getRoomPrice");
		return null;
	}
 	
}
