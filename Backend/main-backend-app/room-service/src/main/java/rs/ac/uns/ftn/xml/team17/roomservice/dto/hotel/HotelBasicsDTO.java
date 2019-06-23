package rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.model.address.Address;
import rs.ac.uns.ftn.xml.team17.roomservice.model.hotel.Hotel;

@NoArgsConstructor
@Getter
@Setter
public class HotelBasicsDTO {
	private Integer id;
	@NotBlank(message = "Plese, enter a PIB.")
	@Size(min = 9, max = 9, message = "PIB must have 9 charachters.")
	private String pib;
	@NotBlank(message = "Plese, enter a hotel name.")
	private String name;
	@Valid @NotNull(message = "Please, enter a hotel address.")
	private Address address;
	
	public HotelBasicsDTO(Hotel hotel) {
		this.pib = hotel.getPib();
		this.id = hotel.getId();
		this.name = hotel.getName();
		this.address = hotel.getAddress();
	}
	
	public Hotel createHotel() {
		Hotel ret = new Hotel();
		ret.setName(name);
		ret.setPib(pib);
		ret.setAddress(address);
		return ret;
	}
}
