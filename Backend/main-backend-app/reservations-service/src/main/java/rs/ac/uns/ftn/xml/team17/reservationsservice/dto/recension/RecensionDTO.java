package rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecensionDTO {
	
	@NotNull(message = "Plese, choose a date.")
	private Date dateOfRecension;
	
	private Double rating;
	protected String comment;
	
	@NotNull(message = "Please, choose a reservation.")
	private Integer reservationId;
}
