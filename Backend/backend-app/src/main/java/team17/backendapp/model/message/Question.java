
package team17.backendapp.model.message;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Java class for Question complex type.
 * </p>
 */
//Database annotations
@Entity
@DiscriminatorValue("question")
//Lambok annotations
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Question", namespace = "http://www.tim17.com/message", propOrder = { "answer" })
public class Question extends Message {

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "question")
	@XmlElement(namespace = "http://www.tim17.com/message")
	protected Answer answer;

}
