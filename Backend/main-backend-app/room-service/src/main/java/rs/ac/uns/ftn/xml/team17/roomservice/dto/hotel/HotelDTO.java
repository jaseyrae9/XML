package rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.model.address.Address;
import rs.ac.uns.ftn.xml.team17.roomservice.model.hotel.Hotel;

@NoArgsConstructor
@Getter
@Setter
public class HotelDTO {
	
	@NotBlank(message = "Plese, enter a hotel name.")
	private String name;
	
	@NotNull(message = "Please, enter a hotel address.")
	private Address address;
	
	@NotBlank(message = "Plese, enter a hotel pib.")
	private String pib;
	
	public HotelDTO(Hotel hotel) {
		this.name = hotel.getName();
		this.address = hotel.getAddress();
		this.pib = hotel.getPib();
	}

}
