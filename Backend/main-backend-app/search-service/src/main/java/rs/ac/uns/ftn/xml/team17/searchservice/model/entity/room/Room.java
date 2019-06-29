
package rs.ac.uns.ftn.xml.team17.searchservice.model.entity.room;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.additionalService.AdditionalService;
import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.address.Address;
import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.roomCategory.RoomCategory;
import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.roomType.RoomType;

/**
 * <p>
 * Java class for Room complex type.
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
@XmlType(name = "Room", namespace = "http://www.tim17.com/room", propOrder = { "id", "hotel", "address", "type",
		"category", "defaultPrice", "numberOfPeople", "cancelationDays", "additionalServices", "totalRating",
		"roomNumber" })
public class Room{
	@EqualsAndHashCode.Include
	@Id
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room", required = true)
	protected Hotel hotel;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room", required = true)
	protected Address address;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room", required = true)
	protected RoomType type;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room", required = true)
	protected RoomCategory category;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected Double defaultPrice;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected Integer numberOfPeople;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected Integer cancelationDays;

	@ManyToMany(fetch = FetchType.LAZY)
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected List<AdditionalService> additionalServices;

	@Column
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected Integer roomNumber;
	
	@Column(nullable = false)
	protected Integer numberOfRatings = 0;
	
	@Column(nullable = false)
	protected Double totalRating = 0.0;

}
