
package rs.ac.uns.ftn.xml.team17.roomservice.model.price;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.model.room.Room;

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
@NoArgsConstructor
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Price", namespace = "http://www.tim17.com/price", propOrder = { "id", "room", "amount", "date" })
public class Price {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_generator")
	@SequenceGenerator(name="price_generator", sequenceName = "price_seq")
	@XmlElement(namespace = "http://www.tim17.com/price")
	protected Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/price", required = true)
	protected Room room;
	
	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/price")
	protected Double amount;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@XmlElement(namespace = "http://www.tim17.com/price", required = true)
	@XmlSchemaType(name = "date")
	protected Date date;
	
	public Price(Room room, Double amount, Date date) {
		this.room = room;
		this.amount = amount;
		this.date = date;
	}
}
