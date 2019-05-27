

package rs.ac.uns.ftn.xml.team17.adminservice.model.roomType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

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
//Lambok annotations
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoomType", namespace = "http://www.tim17.com/roomType", propOrder = { "id", "name" })
public class RoomType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roomType_generator")
	@SequenceGenerator(name="roomType_generator", sequenceName = "roomType_seq")
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
}
