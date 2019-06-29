package rs.ac.uns.ftn.xml.team17.roomservice.scheduledtasks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RatingDTO {
		private Integer roomId;
		private Integer numberOfRatings;
		private Double totalRating;
}
