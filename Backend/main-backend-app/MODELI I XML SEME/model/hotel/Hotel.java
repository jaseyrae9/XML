
package team17.backendapp.model.hotel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;
import team17.backendapp.model.address.Address;
import team17.backendapp.model.room.Room;

/**
 * <p>
 * Java class for Hotel complex type.
 * </p>
 */
//Database annotations
@Entity
//Lambok annotations
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hotel", namespace = "http://www.tim17.com/hotel", propOrder = { "id", "address", "pib" })
public class Hotel {
	@Id
	@XmlElement(namespace = "http://www.tim17.com/hotel")
	protected int id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@XmlElement(namespace = "http://www.tim17.com/hotel", required = true)
	protected Address address;

	@Column(nullable = false, unique = true)
	@XmlElement(name = "PIB", namespace = "http://www.tim17.com/hotel", required = true)
	protected String pib;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	protected Set<Room> rooms;
}
