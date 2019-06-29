package rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecensionDTO {
		
	private Double rating;
	private String title;
	private String comment;
	private String username;
	
	@NotNull(message = "Please, choose a reservation.")
	private Integer reservationId;
	
	private Integer roomId;
	private Integer hotelId;
}
