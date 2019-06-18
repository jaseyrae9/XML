package rs.ac.uns.ftn.xml.team17.roomservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.xml.team17.roomservice.model.price.Price;

public interface PriceRepository extends JpaRepository<Price, Integer> {
	
	@Query("SELECT new rs.ac.uns.ftn.xml.team17.roomservice.dto.room.Price(p.date, p.amount) FROM Price p WHERE p.room.id = :id and p.date BETWEEN :from AND :to ORDER BY p.date ASC")
	public List<rs.ac.uns.ftn.xml.team17.roomservice.dto.room.Price> getRoomPrices(Integer id, Date from, Date to);
}
