
package rs.ac.uns.ftn.xml.team17.reservationsservice.model.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;

/**
 * <p>
 * Java class for Customer complex type.,
 * </p>
 */
//Database annotations
@Entity
@DiscriminatorValue("customer")
//Lambok annotations
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", namespace = "http://www.tim17.com/user", propOrder = { "firstName", "lastName", "email" })
public class Customer extends User {

	@Column
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String firstName;
	
	@Column
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String lastName;
	
	@Column(unique = true)
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String email;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	protected List<Reservation> reservations;
}
