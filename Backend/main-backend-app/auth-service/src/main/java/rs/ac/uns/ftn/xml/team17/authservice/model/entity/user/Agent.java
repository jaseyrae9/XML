
package rs.ac.uns.ftn.xml.team17.authservice.model.entity.user;

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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.address.Address;
import rs.ac.uns.ftn.xml.team17.authservice.model.entity.hotel.Hotel;

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
@EqualsAndHashCode(callSuper = true)
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Agent", namespace = "http://www.tim17.com/user", propOrder = { "firstName", "lastName", "address",
		"hotel" })
public class Agent extends User {

	@Column
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String firstName;
	
	@Column
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String lastName;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected Address address;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected Hotel hotel;
}
