package rs.ac.uns.ftn.xml.team17.roomservice.model.image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.dto.soap.addimage.AddImageRequest;
import rs.ac.uns.ftn.xml.team17.roomservice.model.room.Room;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Image {
	
	public Image(AddImageRequest addImageRequest, Room r) {
		this.mainImage = addImageRequest.isMainImage();
		this.image = addImageRequest.getImage();
		this.room = r;
	}

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_generator")
	@SequenceGenerator(name = "image_generator", sequenceName = "image_seq")
	protected Integer id;
	
	@Column(nullable = false)
	protected Boolean mainImage;
	
	@Column
	protected byte[] image;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	protected Room room;

}
