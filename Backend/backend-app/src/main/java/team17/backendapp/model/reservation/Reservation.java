
package team17.backendapp.model.reservation;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;
import team17.backendapp.model.room.Room;
import team17.backendapp.model.user.Customer;

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
		"dateOfReservation", "customer", "hasHappened" })
public class Reservation {
	@Id
	@XmlElement(namespace = "http://www.tim17.com/reservation")
	protected int id;

	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/reservation", required = true)
	protected Room room;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/reservation", required = true)
	@XmlSchemaType(name = "date")
	// TODO: Baza ne zna da cuva ovo
	// protected XMLGregorianCalendar dateOfReservation;
	protected Date dateOfReservation;

	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/reservation")
	protected Customer customer;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/reservation", defaultValue = "false")
	protected boolean hasHappened;
	
	@OrderBy("date ASC")
	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	protected List<DayReservation> dayReservations;
}
