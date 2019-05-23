package rs.ac.uns.ftn.xml.team17.authservice.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.authservice.model.user.Authority;
import rs.ac.uns.ftn.xml.team17.authservice.repository.AuthorityRepository;

@Service
public class AuthorityService {
	@Autowired
	private AuthorityRepository authorityRepository;
	
	/**
	 * Looks for user authority with given name. If no authotity with given name is found, 
	 * inserts new one into database.
	 * @param name of the authority
	 * @return
	 */
	public Authority findByName(String name){
		Optional<Authority> authority = authorityRepository.findByName(name);
		if( authority.isPresent() ) {
			return authority.get();
		}
		else {
			return saveAuthority(name);
		}
	}
	
	private Authority saveAuthority(String name) {
		Authority authority = new Authority();
		authority.setName(name);
		return authorityRepository.save(authority);
	}
}
