package rs.ac.uns.ftn.xml.team17.roomservice.controller.room;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.room.Price;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.room.RoomFull;
import rs.ac.uns.ftn.xml.team17.roomservice.model.room.Room;
import rs.ac.uns.ftn.xml.team17.roomservice.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
	@Autowired 
	private RoomService roomService;
	
	/**
	 * Returns details about room with given id.
	 * @param roomId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{roomId}")
	public ResponseEntity<RoomFull> getRoom(@PathVariable Integer roomId){
		Room room = roomService.getRoom(roomId);
		return new ResponseEntity<>(new RoomFull(room), HttpStatus.OK);
	}
	
	/**
	 * Returns prices for room with given id, in given range.
	 * @param roomId
	 * @param from
	 * @param to
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{roomId}/price")
	public ResponseEntity<List<Price>> getRoomPrice(@PathVariable Integer roomId, @NotNull @RequestParam Date from, @NotNull @RequestParam Date to) {
		List<Price> prices = roomService.getPrice(roomId, from, to);
		return new ResponseEntity<>(prices, HttpStatus.OK);
	}
 	
}
