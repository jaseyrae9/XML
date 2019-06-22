package rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecensionResponseDTO {

	private Integer id;

	private Double rating;

	private String title;
	
	private String comment;
	
	private String username;
	
	private Boolean isApproved;
	
	protected Date creationDate;
}
