package rs.ac.uns.ftn.xml.team17.authservice.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import rs.ac.uns.ftn.xml.team17.authservice.model.user.User;

public class CustomUserDetails extends User implements UserDetails {
	private static final long serialVersionUID = -7409628553986490588L;
	
	public CustomUserDetails(final User user)
	{
		super(user);
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Ovo cemo menjati kada dodamo banovanje
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Ako budemo radili da agent mora da menja sifru, mozemo preko ovoga
		return false;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		return super.getUserAuthorities()
				.stream()
				.map(authority -> new SimpleGrantedAuthority("ROLE_" + authority.getAuthority()))
				.collect(Collectors.toList());
	}

}
