package rs.ac.uns.ftn.xml.team17.searchservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.address.Address;
import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.room.Room;
import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.roomCategory.RoomCategory;
import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.roomType.RoomType;

@NoArgsConstructor
@Getter
@Setter
public class RoomPreview {
	private Integer id;
	/*
	 * Hotel details
	 */
	private Hotel hotel;
	/*
	 * Room details
	 */
	private Address address;
	private Integer roomNumber;
	private RoomType type;
	private RoomCategory category;
	private Integer numberOfPeople;
	private Double totalRating;
	/*
	 * Calculated details
	 */
	private Double totalStayPrice;
	private Double distance;
	
	public RoomPreview(Room room) {
		this.id = room.getId();
		this.hotel = room.getHotel();
		this.address = room.getAddress();
		this.roomNumber = room.getRoomNumber();
		this.type = room.getType();
		this.category = room.getCategory();
		this.numberOfPeople = room.getNumberOfPeople();
	}
	
}
