
package team17.backendapp.model.user;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;
import team17.backendapp.model.reservation.Reservation;

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
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", namespace = "http://www.tim17.com/user", propOrder = { "firstName", "lastName", "email" })
public class Customer extends User {

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String firstName;
	
	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String lastName;
	
	@Column(nullable = false, unique = false)
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String email;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true)
	protected Set<Reservation> reservations;
}
