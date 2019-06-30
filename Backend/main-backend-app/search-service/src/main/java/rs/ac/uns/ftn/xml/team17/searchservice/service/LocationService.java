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

	public Location getCoordinates(String address) {
		final String urlAddress = url + address + keyUrl + key;
		RestTemplate restTemplate = new RestTemplate();
		GoogleGeoCodeResponse result = restTemplate.getForObject(urlAddress, GoogleGeoCodeResponse.class);
		return result.getLocation();
	}

	public double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}
		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
}
