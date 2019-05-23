
package team17.backendapp.model.user;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Java class for User complex type.
 * </p>
 */
//Database annotations
@Entity
@Table(name = "users")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = STRING)
//Lambok annotations
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", namespace = "http://www.tim17.com/user", propOrder = { "id", "username", "password",
		"authorities" })
@XmlSeeAlso({ Agent.class, Customer.class, Admin.class })
public abstract class User {

	@Id
	@XmlElement(namespace = "http://www.tim17.com/user")
	protected Integer id;

	@Column(nullable = false, unique = true)
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String username;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@XmlElement(namespace = "http://www.tim17.com/user")
	protected List<Authority> authorities;

}
