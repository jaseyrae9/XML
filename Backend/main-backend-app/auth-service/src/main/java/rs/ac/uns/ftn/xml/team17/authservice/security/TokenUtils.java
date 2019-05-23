package rs.ac.uns.ftn.xml.team17.authservice.security;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils {
	private final TokenParameters tokenParameters;
	private final Log LOGGER = LogFactory.getLog(getClass());
	
	public TokenUtils() {
		this.tokenParameters = new TokenParameters();
	}
	
	private String generateAudience(Device device) {
		String audience = this.tokenParameters.getAUDIENCE_UNKNOWN();
		if (device.isNormal()) {
			audience = this.tokenParameters.getAUDIENCE_WEB();
		} else if (device.isTablet()) {
			audience = this.tokenParameters.getAUDIENCE_TABLET();
		} else if (device.isMobile()) {
			audience = this.tokenParameters.getAUDIENCE_MOBILE();
		}
		return audience;
	}
	
	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + this.tokenParameters.getExpiration() * 1000);
	}
	
	private String generateToken(Map<String, Object> claims) {
		try {
			return Jwts.builder().setClaims(claims).setExpiration(this.generateExpirationDate())
					.signWith(SignatureAlgorithm.HS512, this.tokenParameters.getSecret().getBytes("UTF-8")).compact();
		} catch (UnsupportedEncodingException ex) {
			// didn't want to have this method throw the exception, would rather log it and
			// sign the token like it was before
			LOGGER.warn(ex.getMessage());
			return Jwts.builder().setClaims(claims).setExpiration(this.generateExpirationDate())
					.signWith(SignatureAlgorithm.HS512, this.tokenParameters.getSecret()).compact();
		}
	}
	
	/**
	 * Creates new token for user. Included claims are role, username, device and date of creation.
	 * @param userDetails basic user details containing username and password
	 * @param device for which token is being created
	 * @return
	 */
	public String generateToken(UserDetails userDetails, Device device) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("roles", userDetails.getAuthorities());			
		claims.put("sub", userDetails.getUsername());
		//claims.put("audience", this.generateAudience(device));
		claims.put("created", new Date(System.currentTimeMillis()));
		return this.generateToken(claims);
	}
}
