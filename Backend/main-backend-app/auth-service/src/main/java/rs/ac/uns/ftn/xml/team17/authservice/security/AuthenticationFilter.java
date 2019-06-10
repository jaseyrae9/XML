package rs.ac.uns.ftn.xml.team17.authservice.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import rs.ac.uns.ftn.xml.team17.authservice.service.actionservice.CustomUserDetailsService;

public class AuthenticationFilter extends OncePerRequestFilter {
	private CustomUserDetailsService customUserDetailsService;
	
	public AuthenticationFilter(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String usernameHeader = request.getHeader("Username");
		if(usernameHeader != null) {
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(usernameHeader);
			AuthenticationData authentication = new AuthenticationData(userDetails);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);

	}

}
