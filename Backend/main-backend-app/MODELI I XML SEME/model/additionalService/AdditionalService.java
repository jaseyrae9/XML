
package team17.backendapp.model.additionalService;

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
 * Java class for AdditionalService complex type.
 * </p>
 */

//Database annotations
@Entity
//Lambok annotations
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalService", namespace = "http://www.tim17.com/additionalService", propOrder = { "id", "name" })
public class AdditionalService {

	@Id
	@XmlElement(namespace = "http://www.tim17.com/additionalService")
	protected int id;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/additionalService", required = true)
	protected String name;

}
