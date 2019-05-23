package rs.ac.uns.ftn.xml.team17.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.xml.team17.authservice.model.user.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer>{
	Optional<Authority> findByName(String name);
}
