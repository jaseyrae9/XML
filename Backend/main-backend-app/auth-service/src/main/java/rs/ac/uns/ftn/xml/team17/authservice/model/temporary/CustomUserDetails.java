package rs.ac.uns.ftn.xml.team17.authservice.model.temporary;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.User;

public class CustomUserDetails extends User implements UserDetails {
	private static final long serialVersionUID = -7409628553986490588L;
	
	public CustomUserDetails(final User user)
	{
		super(user);
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !getBlocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return getActive();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		return super.getUserAuthorities()
				.stream()
				.map(authority -> new SimpleGrantedAuthority("ROLE_" + authority.getAuthority()))
				.collect(Collectors.toList());
	}
}
