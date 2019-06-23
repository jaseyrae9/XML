package rs.ac.uns.ftn.xml.team17.roomservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rs.ac.uns.ftn.xml.team17.roomservice.dto.hotel.HotelBasicsDTO;
import rs.ac.uns.ftn.xml.team17.roomservice.model.hotel.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer>{
	@Query("SELECT h FROM Hotel h")
	public Page<HotelBasicsDTO> findAllBasics (Pageable pagable);
}
