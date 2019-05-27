package rs.ac.uns.ftn.xml.team17.authservice.dto.registration;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.Customer;

@NoArgsConstructor
@Getter
@Setter
public class CustomerRegistrationDTO {
	@NotBlank(message = "Please, enter a username.")
	private String username;
	
	@NotBlank(message = "Please, enter a password.")
	private String password;
	
	@NotBlank(message = "Please, enter a firstname.")
	private String firstName;
	
	@NotBlank(message = "Please, enter a lastname.")
	private String lastName;
	
	@NotBlank(message = "Please, enter a email.")
	@Email(message = "Email is not in correct format.")
	private String email;
	
	/**
	 * Create customer based on user input from registration form.
	 * Does not set the password.
	 * @return
	 */
	public Customer createCustomer() {
		Customer ret = new Customer();
		ret.setUsername(this.username);
		ret.setFirstName(this.firstName);
		ret.setLastName(this.lastName);
		ret.setEmail(this.email);
		return ret;
	}
}
