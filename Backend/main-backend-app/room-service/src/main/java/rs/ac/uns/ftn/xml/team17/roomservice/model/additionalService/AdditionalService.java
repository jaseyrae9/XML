
package rs.ac.uns.ftn.xml.team17.roomservice.model.additionalService;

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
 * Java class for AdditionalService complex type.
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
@XmlType(name = "AdditionalService", namespace = "http://www.tim17.com/additionalService", propOrder = { "id", "name" })
public class AdditionalService {

	@EqualsAndHashCode.Include
	@Id	
	@XmlElement(namespace = "http://www.tim17.com/additionalService")
	protected Integer id;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/additionalService", required = true)
	protected String name;
	
	@Column(nullable = false)
	private Boolean active;

	public AdditionalService(String name) {
		super();
		this.name = name;
		this.active = true; // when created it is active
	}

}
