package rs.ac.uns.ftn.xml.team17.reservationsservice.dto.reservation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.address.Address;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation.ReservationStatus;

@NoArgsConstructor
@Getter
@Setter
public class ReservationDTO {
	/*
	 * Detalji o rezervaciji
	 */
	private Integer id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd  HH:mm:ss")
	private Date created;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate start;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate end;
	private Double totalPrice;
	private ReservationStatus status;
	private Boolean canCancel;
	private Boolean hasRecension;
	/*
	 * Detalji o hotelu
	 */
	private Hotel hotel;
	/*
	 * Detalji o sobi
	 */
	private Integer roomId;
	private Address address;
	private Integer roomNumber;
	private Integer numberOfPeople;
	
	public ReservationDTO(Reservation reservation) {
		this.id = reservation.getId();
		this.created = reservation.getCreationDate();
		this.start = reservation.getDayReservations().get(0).getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.end = reservation.getDayReservations().get(reservation.getDayReservations().size()-1).getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.status = reservation.getStatus();
		this.totalPrice = reservation.getTotal();
		this.canCancel = reservation.canBeCanceled();
		this.hasRecension = reservation.getHasRecension();
				
		this.hotel = reservation.getRoom().getHotel();
		
		this.roomId = reservation.getRoom().getId();
		this.address = reservation.getRoom().getAddress();
		this.roomNumber = reservation.getRoom().getRoomNumber();
		this.numberOfPeople = reservation.getRoom().getNumberOfPeople();
	}
}
