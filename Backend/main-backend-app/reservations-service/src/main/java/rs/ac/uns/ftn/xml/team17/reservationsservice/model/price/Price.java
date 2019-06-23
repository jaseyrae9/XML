
package rs.ac.uns.ftn.xml.team17.reservationsservice.model.price;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.room.Room;

/**
 * <p>
 * Java class for Price complex type.
 * </p>
 */

//Database annotations
@Entity
//Lambok annotations
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Price", namespace = "http://www.tim17.com/price", propOrder = { "id", "room", "amount", "date" })
public class Price {
	@Id
	@EqualsAndHashCode.Include
	@XmlElement(namespace = "http://www.tim17.com/price")
	protected int id;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/price", required = true)
	protected Room room;
	
	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/price")
	protected double amount;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@XmlElement(namespace = "http://www.tim17.com/price", required = true)
	@XmlSchemaType(name = "date")
	protected Date date;
}
