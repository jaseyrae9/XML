
package team17.backendapp.model.room;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;
import team17.backendapp.model.additionalService.AdditionalService;
import team17.backendapp.model.address.Address;
import team17.backendapp.model.hotel.Hotel;
import team17.backendapp.model.price.Price;
import team17.backendapp.model.reservation.Reservation;
import team17.backendapp.model.roomCategory.RoomCategory;
import team17.backendapp.model.roomType.RoomType;

/**
 * <p>
 * Java class for Room complex type.
 * </p>
 */
//Database annotations
@Entity
//Lambok annotations
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Room", namespace = "http://www.tim17.com/room", propOrder = { "id", "hotel", "address", "type",
		"category", "defaultPrice", "numberOfPeople", "cancelationDays", "additionalServices", "description",
		"totalRating", "numberOfRatings", "floor", "roomNumber" })
public class Room {
	@Id
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected int id;

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
	protected double defaultPrice;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected int numberOfPeople;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected int cancelationDays;

	@ManyToMany(fetch = FetchType.LAZY)
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected List<AdditionalService> additionalServices;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room", required = true)
	protected String description;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room", defaultValue = "0")
	protected double totalRating;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room", defaultValue = "0")
	protected int numberOfRatings;

	@Column
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected Integer floor;

	@Column
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected Integer roomNumber;

	@OrderBy("date ASC")
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	protected List<Price> prices;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, orphanRemoval = true)
	protected Set<Reservation> reservations;
}
