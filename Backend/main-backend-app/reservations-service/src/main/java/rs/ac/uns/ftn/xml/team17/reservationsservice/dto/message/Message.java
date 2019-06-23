package rs.ac.uns.ftn.xml.team17.reservationsservice.dto.message;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Message {
	private Integer id;
	private String message;
	private Date dateSent;
	private Boolean fromCustomer;
}
