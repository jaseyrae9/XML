
package rs.ac.uns.ftn.xml.team17.authservice.model.entity.hotel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hotel", namespace = "http://www.tim17.com/hotel", propOrder = { "id", "name", "address", "pib" })
public class Hotel {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_generator")
	@SequenceGenerator(name="hotel_generator", sequenceName = "hotel_seq")
	@XmlElement(namespace = "http://www.tim17.com/hotel")
	protected Integer id;
}
