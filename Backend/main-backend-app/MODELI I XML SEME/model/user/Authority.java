
package team17.backendapp.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Java class for Authority complex type.
 * </p>
 */
//Database annotations
@Entity
//Lambok annotations
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Authority", namespace = "http://www.tim17.com/user", propOrder = { "id", "name" })
public class Authority implements GrantedAuthority{
	private static final long serialVersionUID = -4773662167708555583L;

	@Id
	@XmlElement(namespace = "http://www.tim17.com/user")
	protected int id;
	
	@Column(nullable = false, unique = true)
	@XmlElement(namespace = "http://www.tim17.com/user", required = true)
	protected String name;

	@Override
	public String getAuthority() {
		return name;
	}
}
