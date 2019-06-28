package rs.ac.uns.ftn.xml.team17.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserStatusChangeException extends Exception{
	private static final long serialVersionUID = -3363841502060359280L;
	private Integer id;

	public UserStatusChangeException(Integer id) {
		super("Can not change status of user with id" + id.toString() + ".");
		this.id = id;		
	}
}
