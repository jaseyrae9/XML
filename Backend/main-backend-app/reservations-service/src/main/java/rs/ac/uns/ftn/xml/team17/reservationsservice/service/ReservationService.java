package rs.ac.uns.ftn.xml.team17.reservationsservice.service;

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

}
