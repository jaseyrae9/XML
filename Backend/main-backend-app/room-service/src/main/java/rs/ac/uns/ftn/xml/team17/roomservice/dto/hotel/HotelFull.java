package rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.room.RoomPreview;
import rs.ac.uns.ftn.xml.team17.roomservice.model.address.Address;
import rs.ac.uns.ftn.xml.team17.roomservice.model.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.roomservice.model.room.Room;

@NoArgsConstructor
@Getter
@Setter
public class HotelFull {
	private Integer id;
	private String name;
	private Address address;
	private List<RoomPreview> rooms = new ArrayList<>();
	
	public HotelFull(Hotel hotel) {
		this.id = hotel.getId();
		this.name = hotel.getName();
		this.address = hotel.getAddress();
		if(hotel.getRooms() != null) {
			for(Room room: hotel.getRooms()) {
				this.rooms.add(new RoomPreview(room));
			}
		}
	}
}
