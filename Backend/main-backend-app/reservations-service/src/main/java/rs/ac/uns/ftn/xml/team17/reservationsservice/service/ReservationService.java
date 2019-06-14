package rs.ac.uns.ftn.xml.team17.reservationsservice.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newreservation.NewReservationRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.DayReservation;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation.ReservationStatus;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.room.Room;
import rs.ac.uns.ftn.xml.team17.reservationsservice.repository.ReservationRepository;
import rs.ac.uns.ftn.xml.team17.reservationsservice.repository.RoomRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
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
		
		List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getreservations.Reservation> ret = new ArrayList<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getreservations.Reservation>();
		
		List<Reservation> reservations = reservationRepository.findAllByModificationDateAfter(date);
		for(Reservation reservation: reservations) { 
			rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getreservations.Reservation r = new rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getreservations.Reservation(reservation);
			ret.add(r);
		}
				
		return ret;
	}

	public Reservation newReservation(NewReservationRequest newReservationRequest) {
		System.out.println("ReservatioService newReservation");
		
		Optional<Room> opt = roomRepository.findById(newReservationRequest.getId());
		
		if(!opt.isPresent()) {
			// TODO: exception
		}
	
		Reservation r = new Reservation();
		r.setCustomer(null);
		r.setStatus(Reservation.ReservationStatus.RESERVED);
		r.setRoom(opt.get());
		
		// Dodavanje DayReservacija
		System.out.println(newReservationRequest.getDateFrom());
		LocalDate start = newReservationRequest.getDateFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = newReservationRequest.getDateTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		for (LocalDate date = start; date.isBefore(end) || date.isEqual(end); date = date.plusDays(1))
		{
		    System.out.println(date);
		    DayReservation dr = new DayReservation();
		    Date dayReservationDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		    dr.setDate(dayReservationDate);
		    dr.setPrice(0); // TODO: postavljanje cene
		    r.addDayReservation(dr);
		}
		
		System.out.println("Prosli kroz for");
		return reservationRepository.save(r);
	}

}
