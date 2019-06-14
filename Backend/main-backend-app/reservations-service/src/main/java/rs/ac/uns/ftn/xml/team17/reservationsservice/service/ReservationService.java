package rs.ac.uns.ftn.xml.team17.reservationsservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation.ReservationStatus;
import rs.ac.uns.ftn.xml.team17.reservationsservice.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	public void confirmReservation(Integer id) {
		Optional<Reservation> opt =  reservationRepository.findById(id);
		
		if(!opt.isPresent()) {
			// TODO: exception
		}
		
		Reservation r = opt.get();
		r.setStatus(ReservationStatus.HAPPENED);
		reservationRepository.save(r);
	}

	public List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getreservations.Reservation> getReservations(Date date) {
		System.out.println("U reservation servisu");
		System.out.println(date);
		
		// TODO: Ovde treba da se preuzme samo nakon datuma date
		reservationRepository.findAll();
		
		
		// TODO: ovde cu ih ja konvertovati u ove XML objekte
		
		
		return null;
	}

}
