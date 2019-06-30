package rs.ac.uns.ftn.xml.team17.searchservice.model.temporary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoogleGeoCodeResponse {
	private String status;
	private Result[] results;
	
	public Location getLocation() {			
		if(results.length == 0) {
			return new Location(0.0, 0.0);
		}
		return results[0].getGeometry().getLocation();
	}
}
