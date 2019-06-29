package rs.ac.uns.ftn.xml.team17.reservationsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RecensionException extends Exception{
	private static final long serialVersionUID = -5469374456609910707L;
	private Integer id;
	
	public RecensionException(Integer id) {
		super("Can not add recension to reservation with " + id.toString() + ". Reservation has not been marked as HAPPENED yet.");
	}
}
