package rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecensionResponseDTO {

	private Integer reservationId;
	
	private Integer roomId;

	private String id;

	private Double rating;

	private String title;

	private String comment;

	private String username;

	private Boolean isApproved;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date creationDate;
}
