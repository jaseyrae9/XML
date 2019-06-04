package rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.model.address.Address;

@NoArgsConstructor
@Getter
@Setter
public class HotelPreview {
	private Integer id;
	private String name;
	private Address address;
}
