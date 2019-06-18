package rs.ac.uns.ftn.xml.team17.authservice.dto.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.Customer;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.User;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {
	private Integer id;
	private String username;
	private String firstName;
	private String lastName;
	private Boolean blocked;
	private Boolean active;

	public CustomerDTO(User customer) {
		this.id = customer.getId();
		this.username = customer.getUsername();
		this.blocked = customer.getBlocked();
		this.active = customer.getActive();
		this.firstName = ((Customer) customer).getFirstName();
		this.lastName = ((Customer) customer).getLastName();
	}	
}
