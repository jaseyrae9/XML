package rs.ac.uns.ftn.xml.team17.roomservice.model.roomCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Where;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Java class for RoomCategory complex type.
 * </p>
 */
//Database annotations
@Entity
@Where(clause="active=true")
//Lambok annotations
@NoArgsConstructor
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
	
	@Column(nullable = false)
	private Boolean active;
	
	public RoomCategory(Integer numberOfStars, String description) {
		super();
		this.numberOfStars = numberOfStars;
		this.description = description;
		
		this.active = true; // when created it is active
	}
	
	public RoomCategory(rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.room.RoomCategory r) {
		super();
		
		this.id = r.getId();
		this.numberOfStars = r.getNumberOfStars();
		this.description = r.getDescription();
		
		this.active = true;
	}
}
