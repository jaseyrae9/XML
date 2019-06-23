
package rs.ac.uns.ftn.xml.team17.reservationsservice.model.user;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.Where;

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
public abstract class User {

	@EqualsAndHashCode.Include
	@Id
	@XmlElement(namespace = "http://www.tim17.com/user")
	protected Integer id;

	@Column(unique = true)
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String username;


	public User(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
	}

}
