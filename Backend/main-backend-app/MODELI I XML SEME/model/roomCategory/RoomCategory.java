
package team17.backendapp.model.roomCategory;

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
 * Java class for RoomCategory complex type.
 * </p>
 */
//Database annotations
@Entity
//Lambok annotations
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomCategory", namespace = "http://www.tim17.com/roomCategory", propOrder = { "id", "numberOfStars",
		"description" })
public class RoomCategory {
	@Id
	@XmlElement(namespace = "http://www.tim17.com/roomCategory")
	protected int id;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/roomCategory")
	protected int numberOfStars;

	@Column
	@XmlElement(namespace = "http://www.tim17.com/roomCategory")
	protected String description;
}
