
package rs.ac.uns.ftn.xml.team17.roomservice.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@NoArgsConstructor
public class Address {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_generator")
	@SequenceGenerator(name="address_generator", sequenceName = "address_seq")
	@XmlElement(namespace = "http://www.tim17.com/address")
	protected Integer id;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address", required = true)
	@NotBlank(message = "Please, enter country.")
	protected String country;

	@Column
	@XmlElement(namespace = "http://www.tim17.com/address")
	protected String state;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address", required = true)
	@NotBlank(message = "Please, enter city.")
	protected String city;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address", required = true)
	@NotBlank(message = "Please, enter postal code.")
	protected String postalCode;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address", required = true)
	@NotBlank(message = "Please, enter street.")
	protected String street;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address", required = true)
	@NotBlank(message = "Please, enter street number.")
	protected String streetNumber;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address")
	@NotNull(message = "Please, enter latitude.")
	@Max(value = 90, message = "Maximal latitude is 90.")
	@Min(value = -90, message = "Minimal latitude is 90.")
	protected Double lat;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/address")
	@NotNull(message = "Please, enter longitude.")
	@Max(value = 180, message = "Maximal longitude is 180.")
	@Min(value = -180, message = "Minimal longitude is -180.")
	protected Double lng;

	public Address(rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.newroom.Address a) {
		this.country = a.getCountry();
		this.state = a.getState();
		this.city = a.getCity();
		this.postalCode = a.getPostalCode();
		this.street = a.getStreet();
		this.streetNumber = a.getStreetNumber();
		this.lat = a.getLat();
		this.lng = a.getLng();
	}
	
}
