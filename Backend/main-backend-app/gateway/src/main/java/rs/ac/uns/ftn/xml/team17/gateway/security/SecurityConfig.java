package rs.ac.uns.ftn.xml.team17.gateway.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		
		http
		// komunikacija izmedju klijenta i servera je stateless
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()		
		// za neautorizovane zahteve posalji 401 gresku
		.exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
		//TODO: Dozvole koristenja putanja bez autentifikacije
		.authorizeRequests()
		.antMatchers("/auth/**").permitAll()
		//TODO: Dozvole po ulogama
		.antMatchers("/admin/**").hasRole("ADMIN")
		// svaki zahtev mora biti autorizovan
		.anyRequest().authenticated();
		
		http.csrf().disable();
	}

}
