package rs.ac.uns.ftn.xml.team17.reservationsservice.dto.message;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.message.Message;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.message.MessageDirection;

@NoArgsConstructor
@Getter
@Setter
public class MessageDTO {
	private Integer id;
	private String message;
	private Date creationDate;
	private MessageDirection status;
	
	public MessageDTO(Message message) {
		this.id = message.getId();
		this.message = message.getMessage();
		this.creationDate = message.getCreationDate();
		this.status = message.getStatus();
	}
}
