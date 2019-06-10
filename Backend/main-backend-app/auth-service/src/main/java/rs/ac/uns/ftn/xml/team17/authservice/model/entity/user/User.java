
package rs.ac.uns.ftn.xml.team17.authservice.model.entity.user;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.Where;

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
@Where(clause="active=true")
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
@Check(constraints = "username IS NOT NULL OR ( username IS NULL AND active = FALSE)")
public abstract class User {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name="user_generator", sequenceName = "user_seq")
	@XmlElement(namespace = "http://www.tim17.com/user")
	protected Integer id;

	@Column(unique = true)
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
	
	@Column(nullable = false)
	private Boolean blocked = false;
	
	@Column(nullable = false)
	private Boolean active = true;

	public User(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities = user.getUserAuthorities();
		this.passwordChangedDate = user.getPasswordChangedDate();
		this.blocked = user.getBlocked();
		this.active = user.getActive();
	}

	public List<Authority> getUserAuthorities() {
		if(authorities == null) {
			authorities = new ArrayList<>();
		}
		return authorities;
	}
	
	public void setPassword(String password) {
		this.password = password;
        this.passwordChangedDate = new Timestamp(System.currentTimeMillis());
	}

}
