
package team17.backendapp.model.message;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;
import team17.backendapp.model.reservation.Reservation;

/**
 * <p>
 * Java class for Message complex type.
 * </p>
 */
//Database annotations
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = STRING)
//Lambok annotations
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Message", namespace = "http://www.tim17.com/message", propOrder = { "id", "reservation", "dateSent",
		"message" })
@XmlSeeAlso({ Answer.class, Question.class })
public abstract class Message {
	@Id
	@XmlElement(namespace = "http://www.tim17.com/message")
	protected int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/message", required = true)
	protected Reservation reservation;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/message", required = true)
	@XmlSchemaType(name = "dateTime")
	// TODO: Baza ne zna da cuva ovo
	// protected XMLGregorianCalendar dateSent;
	protected Date dateSent;

	@Column(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/message", required = true)
	protected String message;
}
