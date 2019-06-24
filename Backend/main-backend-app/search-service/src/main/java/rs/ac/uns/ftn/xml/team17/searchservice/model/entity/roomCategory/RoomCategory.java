
package rs.ac.uns.ftn.xml.team17.searchservice.model.entity.roomCategory;

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
 * Java class for RoomCategory complex type.
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
@XmlType(name = "RoomCategory", namespace = "http://www.tim17.com/roomCategory", propOrder = { "id", "numberOfStars",
		"description" })
public class RoomCategory {
	@EqualsAndHashCode.Include
	@Id
	@XmlElement(namespace = "http://www.tim17.com/roomCategory")
	protected Integer id;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/roomCategory")
	protected Integer numberOfStars;

	@Column
	@XmlElement(namespace = "http://www.tim17.com/roomCategory")
	protected String description;
}
