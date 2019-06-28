package rs.ac.uns.ftn.xml.team17.authservice.exception;

import javax.xml.ws.WebFault;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
@WebFault(name = "NotFound", targetNamespace = "http://examples/")
public class NotFoundException extends Exception {
	private static final long serialVersionUID = 4885186187823425301L;
	private Integer id;
	private String className;
	
	public NotFoundException(Integer id, String className) {
		super(className + " with id " + id.toString() + " not found.");
		this.id = id;
		this.className = className;
	}
	
	public String getFaultInfo() { 
		return super.getMessage();
	}

}
