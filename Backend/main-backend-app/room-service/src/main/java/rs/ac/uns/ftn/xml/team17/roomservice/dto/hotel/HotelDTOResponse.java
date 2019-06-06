package rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.model.address.Address;
import rs.ac.uns.ftn.xml.team17.roomservice.model.hotel.Hotel;

@NoArgsConstructor
@Getter
@Setter
public class HotelDTOResponse {
	
	private Integer id;
	private String name;	
	private Address address;	
	private String pib;
	
	public HotelDTOResponse(Hotel hotel) {
		this.id = hotel.getId();
		this.name = hotel.getName();
		this.address = hotel.getAddress();
		this.pib = hotel.getPib();
	}

}
