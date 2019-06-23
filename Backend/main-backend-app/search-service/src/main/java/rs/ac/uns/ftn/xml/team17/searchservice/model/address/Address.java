
package rs.ac.uns.ftn.xml.team17.searchservice.model.address;

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
 * Java class for Address complex type.
 * </p>
 */
//Database annotations
@Entity
//Lambok annotations
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address", namespace = "http://www.tim17.com/address", propOrder = { "id", "country", "state", "city",
		"postalCode", "street", "streetNumber", "lat", "lng" })
public class Address {
	@EqualsAndHashCode.Include
	@Id
	@XmlElement(namespace = "http://www.tim17.com/address")
	protected Integer id;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address", required = true)
	protected String country;

	@Column
	@XmlElement(namespace = "http://www.tim17.com/address")
	protected String state;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address", required = true)
	protected String city;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address", required = true)
	protected String postalCode;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address", required = true)
	protected String street;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address", required = true)
	protected String streetNumber;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address")
	protected Double lat;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address")
	protected Double lng;

}
