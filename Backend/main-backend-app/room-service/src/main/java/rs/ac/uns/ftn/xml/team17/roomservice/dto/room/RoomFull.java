package rs.ac.uns.ftn.xml.team17.roomservice.dto.room;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.model.additionalService.AdditionalService;
import rs.ac.uns.ftn.xml.team17.roomservice.model.address.Address;
import rs.ac.uns.ftn.xml.team17.roomservice.model.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.roomservice.model.roomCategory.RoomCategory;
import rs.ac.uns.ftn.xml.team17.roomservice.model.roomType.RoomType;

@NoArgsConstructor
@Getter
@Setter
public class RoomFull {
	private Integer id;
	private Hotel hotel;
	private Address address;
	private RoomType type;
	private RoomCategory category;
	private List<AdditionalService> additionalServices;
	private Integer cancelationDays;
	private Integer numberOfPeople;
	private Integer roomNumber;
	private Integer floorNumber;
	private String description;
	private Double totalRating;
	private Integer numberOfRatings;
}
