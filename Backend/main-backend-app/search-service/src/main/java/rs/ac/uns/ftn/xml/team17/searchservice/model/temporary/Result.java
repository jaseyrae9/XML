package rs.ac.uns.ftn.xml.team17.searchservice.model.temporary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Result {
	private String formatted_address;
	private Geometry geometry;
}
