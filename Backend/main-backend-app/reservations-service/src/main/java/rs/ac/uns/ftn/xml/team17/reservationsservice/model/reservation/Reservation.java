
package rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.message.Message;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.room.Room;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.user.Customer;

/**
 * <p>
 * Java class for Reservation complex type.
 * </p>
 */
//Database annotations
@Entity
//Lambok annotations
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reservation", namespace = "http://www.tim17.com/reservation", propOrder = { "id", "room",
		"dateOfReservation", "customer"})
public class Reservation {
	public enum ReservationStatus {RESERVED, HAPPENED, CANCELED};
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_generator")
	@SequenceGenerator(name="reservation_generator", sequenceName = "reservation_seq")
	@XmlElement(namespace = "http://www.tim17.com/reservation")
	protected int id;

	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/reservation", required = true)
	protected Room room;

	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/reservation")
	protected Customer customer;

	@Column(nullable = false)
	//TODO: Dodati XML anotaciju
	protected ReservationStatus status;
	
	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	protected List<Message> messages;
	
	@OrderBy("date ASC")
	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	protected List<DayReservation> dayReservations;

	@CreationTimestamp
	@XmlElement(namespace = "http://www.tim17.com/reservation", required = true)
	@XmlSchemaType(name = "date")
	protected Date creationDate;
	
	@UpdateTimestamp
	protected Date modificationDate;
	
	public void addDayReservation(DayReservation dr) {
		this.dayReservations.add(dr);
	}

	public void addMessage(Message m) {
		this.messages.add(m);
	}
}
