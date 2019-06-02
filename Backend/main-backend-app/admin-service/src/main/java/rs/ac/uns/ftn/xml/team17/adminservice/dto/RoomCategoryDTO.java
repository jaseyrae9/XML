package rs.ac.uns.ftn.xml.team17.adminservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.adminservice.model.roomCategory.RoomCategory;

@NoArgsConstructor
@Getter
@Setter
public class RoomCategoryDTO {
	
	private Integer id;
	
	@NotNull(message = "Plese, enter number of stars.")
	private Integer numberOfStars;
	
	@NotBlank(message = "Please, enter a description.")
	private String description;
	
	public RoomCategoryDTO(RoomCategory roomCategory) {
		this.id = roomCategory.getId();
		this.numberOfStars = roomCategory.getNumberOfStars();
		this.description = roomCategory.getDescription();
	}
	
}


