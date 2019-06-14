//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.06 at 05:23:46 PM CEST 
//


package rs.ac.uns.ftn.xml.team17.reservationsservice.model.recension;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.reservationsservice.model.reservation.Reservation;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Recension", namespace = "http://www.tim17.com/recension", propOrder = {
    "id",
    "isApproved",
    "dateOfRecension",
    "rating",
    "comment"
})
//Database annotations
@Entity
//Lambok annotations
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Recension {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recension_generator")
	@SequenceGenerator(name="recension_generator", sequenceName = "recension_seq")
	@XmlElement(namespace = "http://www.tim17.com/recension")
    protected Integer id;
	
	@Column
    @XmlElement(namespace = "http://www.tim17.com/recension", defaultValue = "false")
    protected Boolean isApproved;
    
    @Column
    @XmlElement(namespace = "http://www.tim17.com/recension")
    protected Double rating;
 
    @Column
    @XmlElement(namespace = "http://www.tim17.com/recension")
    protected String comment;

    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@XmlElement(namespace = "http://www.tim17.com/recension", required = true)
	protected Reservation reservation;
    
    @CreationTimestamp
	@XmlElement(namespace = "http://www.tim17.com/recension", required = true)
	@XmlSchemaType(name = "dateTime")
	protected Date creationDate;
	
	@UpdateTimestamp
	protected Date modificationDate;

}
