package rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.room.RoomPreview;
import rs.ac.uns.ftn.xml.team17.roomservice.model.address.Address;

@NoArgsConstructor
@Getter
@Setter
public class HotelFull {
	private Integer id;
	private String name;
	private Address address;
	private List<RoomPreview> rooms;
}
