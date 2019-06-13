

package rs.ac.uns.ftn.xml.team17.roomservice.model.roomType;

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
 * Java class for RoomType complex type.
 * </p>
 */
//Database annotations
@Entity
@Where(clause="active=true")
//Lambok annotations
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
	
	@Column(nullable = false)
	private Boolean active;
	
	public RoomType(String name) {
		super();
		this.name = name;
		this.active = true; // when created it is active

	}
	
	public RoomType(rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.newroom.RoomType r) { 
		super();
		this.id = r.getId();
		this.active = true;
	}
}
