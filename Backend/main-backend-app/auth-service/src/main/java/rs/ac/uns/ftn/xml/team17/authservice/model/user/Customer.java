
package rs.ac.uns.ftn.xml.team17.authservice.model.user;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
}
