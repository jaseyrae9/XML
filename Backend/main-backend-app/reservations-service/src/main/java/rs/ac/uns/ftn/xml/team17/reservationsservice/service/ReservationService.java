package rs.ac.uns.ftn.xml.team17.reservationsservice.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.reservation.ReservationDTO;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.reservation.ReservationRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.newreservation.NewReservationRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.price.Price;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.DayReservation;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation.ReservationStatus;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.room.Room;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.user.Customer;
import rs.ac.uns.ftn.xml.team17.reservationsservice.repository.DayReservationRepository;
import rs.ac.uns.ftn.xml.team17.reservationsservice.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private DayReservationRepository dayReservationRepository;
	
	@Autowired
	private PriceService priceService;

	@Autowired
	private RoomService roomService;

	/**
	 * Finds reservation with given id.
	 * @param id
	 * @return
	 */
	public Reservation getReservation(Integer id) {
		Optional<Reservation> opt = reservationRepository.findById(id);
		if (!opt.isPresent()) {
			// TODO: exception
		}
		return opt.get();
	}
	
	/**
	 * Finds reservation based on customer id and reservation id.
	 * @param id
	 * @param customer
	 * @return
	 */
	public Reservation getReservation(Integer id, Integer customer) {
		Optional<Reservation> opt = reservationRepository.findByCustomerAndId(id, customer);
		if (!opt.isPresent()) {
			// TODO: exception
			System.out.println("Not found");
		}
		return opt.get();
	}
	
	/**
	 * Finds all reservations belonging to one customer.
	 * @param customer
	 * @return
	 */
	public Page<ReservationDTO> getReservations(Integer customer, Pageable pageable){
		return this.reservationRepository.findCustomerReservation(customer, pageable);
	}
	
	private Boolean isRoomAvailable(Integer roomId, Date from, Date to) {
		Integer count = dayReservationRepository.getAlreadyReservedDays(roomId, from, to);
		if(count > 0) {
			return false;
		}
		return true;
	}
		
	private Reservation createReservation(Integer roomId, Date from, Date to) {
		//TODO: baciti exception
		if(!this.isRoomAvailable(roomId, from, to)) {
			System.err.println("Room not available.");
			return null;
		}
		//create reservation
		Room room = roomService.getRoom(roomId);
		Reservation reservation = new Reservation(room);
		//Get all prices
		List<Price> prices = priceService.getPrices(roomId, from, to);
		//Adding day reservations
		LocalDate start = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) {			
			DayReservation dr = new DayReservation();
			Date dayReservationDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			dr.setDate(dayReservationDate);
			// TODO: ZA MILOSA, uradila sam set price, ako hoces da vidis
			dr.setPrice(priceService.decidePrice(prices, room.getDefaultPrice(), dayReservationDate)); 
			dr.setReservation(reservation);
			reservation.addDayReservation(dr);
		}		
		return reservation;
	}
	
	/**
	 * Creates reservation for customer with given id.
	 * @param reservationRequest - reservation information with requested room, start and end date.
	 * @param customerId - customer id
	 * @return - reservation object
	 */
	public Reservation createReservation(ReservationRequest reservationRequest, Integer customerId) {
		Reservation r = this.createReservation(reservationRequest.getRoomId(), reservationRequest.getStart(), reservationRequest.getEnd());
		r.setCustomer(new Customer(customerId));
		return reservationRepository.save(r);
	}

	public Reservation newReservation(NewReservationRequest newReservationRequest) {
		//TODO: Provera sme li taj agent za tu sobu da napravi rezervaciju
		Reservation r = this.createReservation(newReservationRequest.getId(), newReservationRequest.getDateFrom(), newReservationRequest.getDateTo());
		return reservationRepository.save(r);
	}
	
	/**
	 * Cancels customers reservation if cancelation is possible. For cancelation to be
	 * possible reservation status must be RESERVED. Also, start date of reservation must
	 * be at least room.cancelationDays away.
	 * @param customerId
	 * @param reservationId
	 * @return
	 */
	public Reservation cancelReservation(Integer customerId, Integer reservationId) {
		Reservation reservation = this.getReservation(reservationId, customerId);
		//check if this customer can be canceled
		if(!reservation.canBeCanceled()) {
			//TODO: exception
			System.out.println("can not be canceled");
			return null;
		}
		reservation.setStatus(ReservationStatus.CANCELED);
		return reservation;
	}
	
	public void confirmReservation(Integer id) {
		//TODO: provera sme li taj agent da potvri tu rezervaciju
		Reservation r = getReservation(id);
		if(r.getStatus() != ReservationStatus.RESERVED) {
			// TODO: exception
		}
		r.setStatus(ReservationStatus.HAPPENED);
		reservationRepository.save(r);
	}

	public List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getreservations.Reservation> getReservations(Date date, Integer hotelId) {
		List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getreservations.Reservation> ret = new ArrayList<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getreservations.Reservation>();
		List<Reservation> reservations = reservationRepository.findAll(date, hotelId);
		for (Reservation reservation : reservations) {
			rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getreservations.Reservation r = new rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getreservations.Reservation(
					reservation);
			ret.add(r);
		}
		return ret;
	}

}
