package rs.ac.uns.ftn.xml.team17.searchservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.searchservice.model.address.Address;
import rs.ac.uns.ftn.xml.team17.searchservice.model.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.searchservice.model.roomCategory.RoomCategory;
import rs.ac.uns.ftn.xml.team17.searchservice.model.roomType.RoomType;

@NoArgsConstructor
@Getter
@Setter
public class RoomPreview {
	private Integer roomId;
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
	private Double totalStayPrice;
	
}
