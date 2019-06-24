package rs.ac.uns.ftn.xml.team17.searchservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.price.Price;

public interface PriceRepository extends JpaRepository<Price, Integer> {	
	@Query("SELECT p FROM Price p WHERE p.room.id = :id and p.date BETWEEN :from AND :to ORDER BY p.date ASC")
	public List<Price> getPrices(Integer id, Date from, Date to);
}
