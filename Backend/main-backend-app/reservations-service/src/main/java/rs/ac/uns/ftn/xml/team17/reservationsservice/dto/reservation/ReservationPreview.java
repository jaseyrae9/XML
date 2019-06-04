package rs.ac.uns.ftn.xml.team17.reservationsservice.dto.reservation;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.address.Address;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation.ReservationStatus;

@NoArgsConstructor
@Getter
@Setter
public class ReservationPreview {
	/*
	 * Detalji o rezervaciji
	 */
	private Integer id;
	private Date start;
	private Date end;
	private Double totalPrice;
	private ReservationStatus status;
	private Boolean canCancel;
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
}
