
package team17.backendapp.model.message;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;
import team17.backendapp.model.user.Agent;

/**
 * <p>
 * Java class for Answer complex type.
 * </p>
 */
//Database annotations
@Entity
@DiscriminatorValue("answer")
//Lambok annotations
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Answer", namespace = "http://www.tim17.com/message", propOrder = { "answeredBy" })
public class Answer extends Message {
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	protected Question question;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/message", required = true)
	protected Agent answeredBy;

}
