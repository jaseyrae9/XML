
package team17.backendapp.model.reservation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Java class for DayReservation complex type.
 * </p>
 */
//Database annotations
@Entity
//Lambok annotations
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DayReservation", namespace = "http://www.tim17.com/reservation", propOrder = { "id", "date",
		"roomReservation", "price" })
public class DayReservation {
	@Id
	@XmlElement(namespace = "http://www.tim17.com/reservation")
	protected int id;
	
	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/reservation", required = true)
	@XmlSchemaType(name = "date")
	// TODO: Baza ne zna da cuva ovo
	// protected XMLGregorianCalendar date;
	protected Date date;
	
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/reservation", required = true)
	protected Reservation reservation;
	
	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/reservation")
	protected double price;
}
