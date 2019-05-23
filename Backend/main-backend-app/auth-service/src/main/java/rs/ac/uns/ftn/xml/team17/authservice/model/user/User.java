
package rs.ac.uns.ftn.xml.team17.authservice.model.user;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

import java.sql.Timestamp;
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

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", namespace = "http://www.tim17.com/user", propOrder = { "id", "username", "password",
		"authorities" })
@XmlSeeAlso({ Agent.class, Customer.class, Admin.class })
public abstract class User{

	@EqualsAndHashCode.Include
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
	@Getter(AccessLevel.NONE)
	protected List<Authority> authorities;
	
	@Column
	private Timestamp passwordChangedDate;
	
	public User(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword(); 
		this.authorities = user.getUserAuthorities();
		this.passwordChangedDate = user.getPasswordChangedDate();
	}

	public List<Authority> getUserAuthorities() {
		return authorities;
}

}
