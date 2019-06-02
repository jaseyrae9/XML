package rs.ac.uns.ftn.xml.team17.adminservice.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.adminservice.model.roomType.RoomType;

@NoArgsConstructor
@Getter
@Setter
public class RoomTypeDTO {
	private Integer id;
	
	@NotBlank(message = "Plese, a additional service name.")
	private String name;

	public RoomTypeDTO(RoomType roomType) {
		this.id = roomType.getId();
		this.name = roomType.getName();
	}
}
