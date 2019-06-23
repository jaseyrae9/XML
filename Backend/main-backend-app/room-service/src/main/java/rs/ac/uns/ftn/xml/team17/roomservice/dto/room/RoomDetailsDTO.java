package rs.ac.uns.ftn.xml.team17.roomservice.dto.room;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel.HotelBasicsDTO;
import rs.ac.uns.ftn.xml.team17.roomservice.model.additionalService.AdditionalService;
import rs.ac.uns.ftn.xml.team17.roomservice.model.address.Address;
import rs.ac.uns.ftn.xml.team17.roomservice.model.room.Room;
import rs.ac.uns.ftn.xml.team17.roomservice.model.roomCategory.RoomCategory;
import rs.ac.uns.ftn.xml.team17.roomservice.model.roomType.RoomType;

@NoArgsConstructor
@Getter
@Setter
public class RoomDetailsDTO {
	private Integer id;
	private HotelBasicsDTO hotel; 
	private Address address;
	private RoomType type;
	private RoomCategory category;
	private List<AdditionalService> additionalServices;
	private Integer cancelationDays;
	private Integer numberOfPeople;
	private Integer roomNumber;
	private Integer floorNumber;
	private String description;
	private Double defaultPrice;
	
	public RoomDetailsDTO(Room room) {
		this.id = room.getId();
		this.hotel = new HotelBasicsDTO(room.getHotel());
		this.address = room.getAddress();
		this.type = room.getType();
		this.category = room.getCategory();
		this.additionalServices = room.getAdditionalServices();
		this.cancelationDays = room.getCancelationDays();
		this.numberOfPeople = room.getNumberOfPeople();
		this.roomNumber = room.getRoomNumber();
		this.floorNumber = room.getFloorNumber();
		this.description = room.getDescription();
		this.defaultPrice = room.getDefaultPrice();
	}
}
