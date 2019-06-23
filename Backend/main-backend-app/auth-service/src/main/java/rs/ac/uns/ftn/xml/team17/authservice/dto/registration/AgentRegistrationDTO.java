package rs.ac.uns.ftn.xml.team17.authservice.dto.registration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.Agent;

@NoArgsConstructor
@Getter
@Setter
public class AgentRegistrationDTO {
	@NotBlank(message = "Please, enter a username.")
	private String username;
	
	@NotBlank(message = "Please, enter a firstname.")
	private String firstName;
	
	@NotBlank(message = "Please, enter a lastname.")
	private String lastName;
	
	
	@NotNull(message = "Please, choose a hotel.")
	private Integer hotelId;
	
	public Agent createAgent() {
		Agent agent = new Agent();
		agent.setUsername(this.username);
		agent.setFirstName(this.firstName);
		agent.setLastName(this.lastName);	
		agent.setHotel(new Hotel(hotelId));
		return agent;
	}
}
