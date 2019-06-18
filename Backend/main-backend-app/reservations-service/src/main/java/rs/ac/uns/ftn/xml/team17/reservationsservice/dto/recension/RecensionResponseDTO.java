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
	
	private Boolean isApproved;
	
	private Double rating;
	
	private String comment;
	
	protected Date creationDate;
	
	protected Date modificationDate;
}
