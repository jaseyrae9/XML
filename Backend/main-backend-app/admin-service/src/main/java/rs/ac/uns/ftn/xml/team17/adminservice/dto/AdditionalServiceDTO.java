package rs.ac.uns.ftn.xml.team17.adminservice.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.adminservice.model.additionalService.AdditionalService;

@NoArgsConstructor
@Getter
@Setter
public class AdditionalServiceDTO {

	private Integer id;
	
	@NotBlank(message = "Plese, enter a additional service name.")
	private String name;
	
	public AdditionalServiceDTO(AdditionalService additionalService) {
		this.id = additionalService.getId();
		this.name = additionalService.getName();
	}
}
