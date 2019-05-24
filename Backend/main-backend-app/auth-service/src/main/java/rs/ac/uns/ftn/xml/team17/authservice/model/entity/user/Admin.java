
package rs.ac.uns.ftn.xml.team17.authservice.model.entity.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import lombok.EqualsAndHashCode;

/**
 * <p>
 * Java class for Admin complex type.
 * </p>
 */
//Database annotations
@Entity
@DiscriminatorValue("admin")
//Lombok annotations
@EqualsAndHashCode(callSuper = true)
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Admin", namespace = "http://www.tim17.com/user")
public class Admin extends User {

}
