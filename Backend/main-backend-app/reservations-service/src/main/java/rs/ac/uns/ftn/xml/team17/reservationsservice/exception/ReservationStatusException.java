package rs.ac.uns.ftn.xml.team17.reservationsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ReservationStatusException extends Exception {
	private static final long serialVersionUID = -5469374456609910707L;
	private Integer id;
	
	public ReservationStatusException(Integer id) {
		super("Status of reservation with id " + id.toString() + " can not be updated.");
	}
	
}
