
package rs.ac.uns.ftn.xml.team17.reservationsservice.model.room;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.address.Address;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.price.Price;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;

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
@XmlType(name = "Room", namespace = "http://www.tim17.com/room", propOrder = { "id", "hotel", "address", 
		"defaultPrice", "numberOfPeople", "cancelationDays", "roomNumber" })
public class Room {
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

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected Double defaultPrice;
	
	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected Integer numberOfPeople;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected Integer cancelationDays;
	
	@Column
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected Integer roomNumber;

	@OrderBy("date ASC")
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	protected List<Price> prices;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, orphanRemoval = true)
	protected Set<Reservation> reservations;
}
