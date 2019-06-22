package rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RoomRecensionDTO {

	private List<RecensionResponseDTO> retRecensions;
	
	private Integer ratingCount;
	private Double totalRating;
	
}
