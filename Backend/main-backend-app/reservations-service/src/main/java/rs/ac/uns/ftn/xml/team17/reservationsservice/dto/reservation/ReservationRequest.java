package rs.ac.uns.ftn.xml.team17.reservationsservice.dto.reservation;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReservationRequest {
	@NotNull(message = "Please, choose room.")
	private Integer roomId;
	@NotNull(message = "Please, enter start date of your visit.")
	private Date start;
	@NotNull(message = "Please, enter end date of your visit.")
	private Date end;	
}
