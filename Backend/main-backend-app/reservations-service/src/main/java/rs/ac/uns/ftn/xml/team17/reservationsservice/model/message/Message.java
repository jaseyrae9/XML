//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 04:25:21 PM CEST 
//

package rs.ac.uns.ftn.xml.team17.reservationsservice.model.message;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Message", propOrder = { "id", "dateSent", "message", "status" })
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_generator")
	@SequenceGenerator(name = "message_generator", sequenceName = "message_seq")
	protected Integer id;

	@XmlElement(required = true)
	protected String message;
	
	@XmlElement(required = true)
	protected MessageDirection status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	protected Reservation reservation;

	@CreationTimestamp
	@XmlElement(namespace = "http://www.tim17.com/message", required = true)
	@XmlSchemaType(name = "dateTime")
	protected Date creationDate;

	@UpdateTimestamp
	protected Date modificationDate;

	public Message(String message, Reservation r) {
		this.message = message;
		this.reservation = r;
	}
}
