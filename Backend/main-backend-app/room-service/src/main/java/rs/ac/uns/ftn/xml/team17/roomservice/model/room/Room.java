
package rs.ac.uns.ftn.xml.team17.roomservice.model.room;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.model.additionalService.AdditionalService;
import rs.ac.uns.ftn.xml.team17.roomservice.model.address.Address;
import rs.ac.uns.ftn.xml.team17.roomservice.model.hotel.Hotel;
import rs.ac.uns.ftn.xml.team17.roomservice.model.image.Image;
import rs.ac.uns.ftn.xml.team17.roomservice.model.price.Price;
import rs.ac.uns.ftn.xml.team17.roomservice.model.roomCategory.RoomCategory;
import rs.ac.uns.ftn.xml.team17.roomservice.model.roomType.RoomType;

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
		"category", "defaultPrice", "numberOfPeople", "cancelationDays", "additionalServices", "description", "floorNumber", "roomNumber" })
@NoArgsConstructor
public class Room {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_generator")
	@SequenceGenerator(name="room_generator", sequenceName = "room_seq")
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

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/room", required = true)
	protected String description;
	
	@Column
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected Integer roomNumber;
	
	@Column
	@XmlElement(namespace = "http://www.tim17.com/room")
	protected Integer floorNumber;

	@OrderBy("date ASC")
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	protected List<Price> prices;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	protected List<Image> images;
	
	public Room(rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.newroom.Room r) {
		this.address = new Address(r.getAddress());
		this.type = new RoomType(r.getType());
		this.category = new RoomCategory(r.getCategory());
		this.defaultPrice = r.getDefaultPrice();
		this.numberOfPeople = r.getNumberOfPeople();
		this.cancelationDays = r.getCancelationDays();
		this.additionalServices = new ArrayList<AdditionalService>();
		for(rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.newroom.AdditionalService a : r.getAdditionalServices()) {
			rs.ac.uns.ftn.xml.team17.roomservice.model.additionalService.AdditionalService as = new AdditionalService(a);
			this.additionalServices.add(as);
		}
		this.description = r.getDescription();
		this.roomNumber = r.getRoomNumber();
		this.floorNumber = r.getFloor();
	}

	public void setPrice(LocalDate date, double d) {
		Date priceDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()); // Datum za koji se postavlja cena dd
		boolean exists = false;
		for(Price p : this.prices) {
			if(priceDate.equals(p.getDate())) {
				p.setAmount(d);
				exists = true;
				break;
			}
		}
		
		if(!exists) {
			Price p = new Price();
			p.setDate(priceDate);
			p.setAmount(new Double(d));
			p.setRoom(this);
			this.prices.add(p);
		}
		
	}

	public void addImage(Image newImage) {
		this.images.add(newImage);
	}

}
