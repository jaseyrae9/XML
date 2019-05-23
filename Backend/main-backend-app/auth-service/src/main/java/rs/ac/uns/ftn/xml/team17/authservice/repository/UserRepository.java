package rs.ac.uns.ftn.xml.team17.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.xml.team17.authservice.model.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
