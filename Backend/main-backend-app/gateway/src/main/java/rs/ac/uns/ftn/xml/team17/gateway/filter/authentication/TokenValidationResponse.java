package rs.ac.uns.ftn.xml.team17.gateway.filter.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TokenValidationResponse {
	private Boolean isValid = false;
	private Integer userId;
	private String username;
	private String authorities;
	private Integer hotelId;
}
