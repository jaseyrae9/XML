package rs.ac.uns.ftn.xml.team17.roomservice.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String usernameHeader = request.getHeader("Username");
		if(usernameHeader != null) {
			CustomUserDetails userDetails = new CustomUserDetails(usernameHeader, request.getHeader("Authorities"));
			AuthenticationData authentication = new AuthenticationData(userDetails);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

}
