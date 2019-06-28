
package rs.ac.uns.ftn.xml.team17.searchservice.model.entity.reservation;

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

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DayReservation", namespace = "http://www.tim17.com/reservation", propOrder = { "id", "date",
		"reservation", "price" })
public class DayReservation {
	@EqualsAndHashCode.Include
	@Id
	@XmlElement(namespace = "http://www.tim17.com/reservation")
	protected Integer id;
	
	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/reservation", required = true)
	@XmlSchemaType(name = "date")
	protected Date date;
	
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/reservation", required = true)
	protected Reservation reservation;
	
	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/reservation")
	protected Double price;
}
