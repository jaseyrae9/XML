package rs.ac.uns.ftn.xml.team17.roomservice.dto.room;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.model.address.Address;
import rs.ac.uns.ftn.xml.team17.roomservice.model.roomCategory.RoomCategory;
import rs.ac.uns.ftn.xml.team17.roomservice.model.roomType.RoomType;

@NoArgsConstructor
@Getter
@Setter
public class RoomPreview {
	private Integer id;
	private Address address;
	private RoomType type;
	private RoomCategory category;
	private Integer numberOfPeople;
	private Integer roomNumber;
}
