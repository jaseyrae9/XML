
package team17.backendapp.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Admin complex type.
 * </p>
 */
//Database annotations
@Entity
@DiscriminatorValue("admin")
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Admin", namespace = "http://www.tim17.com/user")
public class Admin extends User {

}
