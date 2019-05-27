package rs.ac.uns.ftn.xml.team17.adminservice.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 8107841525693148549L;
	private String username;
	private List<String> authorities;
	
	public CustomUserDetails(String username, String authorities) {
		this.username = username;
		this.authorities = new ArrayList<String>();
		this.authorities = new ArrayList<String>(Arrays.asList(authorities.split(" , ")));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return 	authorities
				.stream()
				.map(authority -> new SimpleGrantedAuthority(authority))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
