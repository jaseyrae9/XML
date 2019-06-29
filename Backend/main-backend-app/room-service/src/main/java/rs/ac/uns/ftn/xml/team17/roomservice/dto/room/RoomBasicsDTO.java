package rs.ac.uns.ftn.xml.team17.roomservice.dto.room;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.model.address.Address;
import rs.ac.uns.ftn.xml.team17.roomservice.model.room.Room;
import rs.ac.uns.ftn.xml.team17.roomservice.model.roomCategory.RoomCategory;
import rs.ac.uns.ftn.xml.team17.roomservice.model.roomType.RoomType;

@NoArgsConstructor
@Getter
@Setter
public class RoomBasicsDTO {

	private Integer id;
	private Address address;
	private RoomType type;
	private RoomCategory category;
	private Integer numberOfPeople;
	private Integer roomNumber;
	private byte[] mainImage;
	
	public RoomBasicsDTO(Room room) {
		this.id = room.getId();
		this.address = room.getAddress();
		this.type = room.getType();
		this.category = room.getCategory();
		this.numberOfPeople = room.getNumberOfPeople();
		this.roomNumber = room.getRoomNumber();
		this.mainImage = room.getMainImage();
	}
}
