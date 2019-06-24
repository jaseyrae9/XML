package rs.ac.uns.ftn.xml.team17.searchservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rs.ac.uns.ftn.xml.team17.searchservice.model.temporary.GoogleGeoCodeResponse;
import rs.ac.uns.ftn.xml.team17.searchservice.model.temporary.Location;

@Service
public class LocationService {
	private final String key = "AIzaSyAha_PuYkkB226RBgsn81j3CP7vG-Mv1ig";
	private final String url = "https://maps.googleapis.com/maps/api/geocode/json?address=";
	private final String keyUrl = "+CA&key=";	

	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

	public Location getCoordinates(String address) {
		final String urlAddress = url + address + keyUrl + key;
		RestTemplate restTemplate = new RestTemplate();
		GoogleGeoCodeResponse result = restTemplate.getForObject(urlAddress, GoogleGeoCodeResponse.class);
		return result.getLocation();
	}
	

    /**
     * User Haversin formula to calculate distance between two coordinates.
     * @param l1
     * @param l2
     * @return
     */
    public Double distance(Location l1, Location l2) {

        Double dLat  = Math.toRadians((l2.getLat() - l1.getLat()));
        Double dLong = Math.toRadians((l2.getLng() - l1.getLng()));

        Double startLat = Math.toRadians(l1.getLat());
        Double endLat   = Math.toRadians(l2.getLat());

        Double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    private Double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
