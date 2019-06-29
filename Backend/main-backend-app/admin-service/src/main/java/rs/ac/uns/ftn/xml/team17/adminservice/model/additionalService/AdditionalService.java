
package rs.ac.uns.ftn.xml.team17.adminservice.model.additionalService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Where;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Database annotations
@Entity
@Where(clause="active=true")
//Lambok annotations
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
//XML annotations
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalService", namespace = "http://www.tim17.com/additionalService", propOrder = { "id", "name" })
public class AdditionalService {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "additionalService_generator")
	@SequenceGenerator(name="additionalService_generator", sequenceName = "additionalService_seq")
	@XmlElement(namespace = "http://www.tim17.com/additionalService")
	protected Integer id;

	
	@Column(nullable = false)
	@NotBlank(message = "Please, enter additional service name.")
	@XmlElement(namespace = "http://www.tim17.com/additionalService", required = true)
	protected String name;
	
	@Column(nullable = false)
	private Boolean active;

	public AdditionalService(String name) {
		super();
		this.name = name;
		this.active = true; // when created it is active
	}

}
