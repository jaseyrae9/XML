
package rs.ac.uns.ftn.xml.team17.searchservice.model.price;

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
import rs.ac.uns.ftn.xml.team17.searchservice.model.room.Room;

/**
 * <p>
 * Java class for Price complex type.
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
@XmlType(name = "Price", namespace = "http://www.tim17.com/price", propOrder = { "id", "room", "amount", "date" })
public class Price {
	@EqualsAndHashCode.Include
	@Id
	@XmlElement(namespace = "http://www.tim17.com/price")
	protected Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/price", required = true)
	protected Room room;
	
	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/price")
	protected Double amount;
	
	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/price", required = true)
	@XmlSchemaType(name = "date")
	// TODO: Baza ne zna da cuva ovo
	// protected XMLGregorianCalendar date; 
	protected Date date;
}
