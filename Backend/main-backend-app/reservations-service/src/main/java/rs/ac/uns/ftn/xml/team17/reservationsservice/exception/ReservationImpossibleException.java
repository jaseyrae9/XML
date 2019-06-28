package rs.ac.uns.ftn.xml.team17.reservationsservice.exception;

import java.util.Date;

import javax.xml.ws.WebFault;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@WebFault(name = "NotFound", targetNamespace = "http://examples/")
public class ReservationImpossibleException extends Exception{
	private static final long serialVersionUID = -5940706653735481549L;
	private Date start;
	private Date end;
	
	public ReservationImpossibleException(Date start, Date end) {
		super("Can not create reservation from " + start + " to" + end + "Room is aleardy taken.");
		this.start = start;
		this.end = end;
	}
	
	public String getFaultInfo() { 
		return super.getMessage();
	}
}
