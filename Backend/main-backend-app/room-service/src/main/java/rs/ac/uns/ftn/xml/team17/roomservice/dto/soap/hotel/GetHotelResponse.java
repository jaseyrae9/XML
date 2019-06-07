//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.07 at 06:40:26 PM CEST 
//


package rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.hotel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Room" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="address" type="{http://www.team17.xml.ftn.uns.ac.rs/hotel}Address"/>
 *                   &lt;element name="type" type="{http://www.team17.xml.ftn.uns.ac.rs/hotel}RoomType"/>
 *                   &lt;element name="category" type="{http://www.team17.xml.ftn.uns.ac.rs/hotel}RoomCategory"/>
 *                   &lt;element name="numberOfPeople" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="roomNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "room"
})
@XmlRootElement(name = "getHotelResponse")
public class GetHotelResponse {

    @XmlElement(name = "Room", required = true)
    protected List<GetHotelResponse.Room> room;

    /**
     * Gets the value of the room property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the room property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetHotelResponse.Room }
     * 
     * 
     */
    public List<GetHotelResponse.Room> getRoom() {
        if (room == null) {
            room = new ArrayList<GetHotelResponse.Room>();
        }
        return this.room;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="address" type="{http://www.team17.xml.ftn.uns.ac.rs/hotel}Address"/>
     *         &lt;element name="type" type="{http://www.team17.xml.ftn.uns.ac.rs/hotel}RoomType"/>
     *         &lt;element name="category" type="{http://www.team17.xml.ftn.uns.ac.rs/hotel}RoomCategory"/>
     *         &lt;element name="numberOfPeople" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="roomNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "id",
        "address",
        "type",
        "category",
        "numberOfPeople",
        "roomNumber"
    })
    public static class Room {

        protected int id;
        @XmlElement(required = true)
        protected Address address;
        @XmlElement(required = true)
        protected RoomType type;
        @XmlElement(required = true)
        protected RoomCategory category;
        protected int numberOfPeople;
        protected int roomNumber;

        /**
         * Gets the value of the id property.
         * 
         */
        public int getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         */
        public void setId(int value) {
            this.id = value;
        }

        /**
         * Gets the value of the address property.
         * 
         * @return
         *     possible object is
         *     {@link Address }
         *     
         */
        public Address getAddress() {
            return address;
        }

        /**
         * Sets the value of the address property.
         * 
         * @param value
         *     allowed object is
         *     {@link Address }
         *     
         */
        public void setAddress(Address value) {
            this.address = value;
        }

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link RoomType }
         *     
         */
        public RoomType getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link RoomType }
         *     
         */
        public void setType(RoomType value) {
            this.type = value;
        }

        /**
         * Gets the value of the category property.
         * 
         * @return
         *     possible object is
         *     {@link RoomCategory }
         *     
         */
        public RoomCategory getCategory() {
            return category;
        }

        /**
         * Sets the value of the category property.
         * 
         * @param value
         *     allowed object is
         *     {@link RoomCategory }
         *     
         */
        public void setCategory(RoomCategory value) {
            this.category = value;
        }

        /**
         * Gets the value of the numberOfPeople property.
         * 
         */
        public int getNumberOfPeople() {
            return numberOfPeople;
        }

        /**
         * Sets the value of the numberOfPeople property.
         * 
         */
        public void setNumberOfPeople(int value) {
            this.numberOfPeople = value;
        }

        /**
         * Gets the value of the roomNumber property.
         * 
         */
        public int getRoomNumber() {
            return roomNumber;
        }

        /**
         * Sets the value of the roomNumber property.
         * 
         */
        public void setRoomNumber(int value) {
            this.roomNumber = value;
        }

    }

}
