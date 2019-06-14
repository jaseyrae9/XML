package rs.ac.uns.ftn.xml.team17.reservationsservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.xml.team17.reservationsservice.model.recension.Recension;

public interface RecensionRepository  extends JpaRepository<Recension, Integer>  {

	List<Recension> findAllByModificationDateAfter (Date date);
}
