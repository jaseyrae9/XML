
package team17.backendapp.model.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;
import team17.backendapp.model.address.Address;
import team17.backendapp.model.hotel.Hotel;

/**
 * <p>
 * Java class for Agent complex type.
 * </p>
 */
//Database annotations
@Entity
@DiscriminatorValue("agent")
//Lambok annotations
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Agent", namespace = "http://www.tim17.com/user", propOrder = { "firstName", "lastName", "address",
		"hotel" })
public class Agent extends User {

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String firstName;
	
	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String lastName;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected Address address;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected Hotel hotel;
}
