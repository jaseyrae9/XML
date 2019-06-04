
package rs.ac.uns.ftn.xml.team17.searchservice.model.roomType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Java class for RoomType complex type.
 * </p>
 */
//Database annotations
@Entity
//Lambok annotations
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomType", namespace = "http://www.tim17.com/roomType", propOrder = { "id", "name" })
public class RoomType {
	@EqualsAndHashCode.Include
	@Id
	@XmlElement(namespace = "http://www.tim17.com/roomType")
	protected Integer id;

	@Column(nullable = false, unique = true)
	@XmlElement(namespace = "http://www.tim17.com/roomType", required = true)
	protected String name;
}
