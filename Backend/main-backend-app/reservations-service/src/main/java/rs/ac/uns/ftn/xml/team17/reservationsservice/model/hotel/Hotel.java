
package rs.ac.uns.ftn.xml.team17.reservationsservice.model.hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

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
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hotel", namespace = "http://www.tim17.com/hotel", propOrder = { "id", "name" })
public class Hotel {
	@Id
	@XmlElement(namespace = "http://www.tim17.com/hotel")
	protected Integer id;

	@Column(nullable = false)
	@XmlElement(name = "name", namespace = "http://www.tim17.com/hotel", required = true)
	protected String name;
}
