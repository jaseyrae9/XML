
package rs.ac.uns.ftn.xml.team17.authservice.model.hotel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.authservice.model.address.Address;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hotel", namespace = "http://www.tim17.com/hotel", propOrder = { "id", "address", "pib" })
public class Hotel {
	@EqualsAndHashCode.Include
	@Id
	@XmlElement(namespace = "http://www.tim17.com/hotel")
	protected Integer id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@XmlElement(namespace = "http://www.tim17.com/hotel", required = true)
	protected Address address;

	@Column(nullable = false, unique = true)
	@XmlElement(name = "PIB", namespace = "http://www.tim17.com/hotel", required = true)
	protected String pib;
}
