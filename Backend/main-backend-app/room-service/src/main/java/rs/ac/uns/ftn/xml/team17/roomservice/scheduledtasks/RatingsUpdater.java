package rs.ac.uns.ftn.xml.team17.roomservice.scheduledtasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import rs.ac.uns.ftn.xml.team17.roomservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.roomservice.model.room.Room;
import rs.ac.uns.ftn.xml.team17.roomservice.service.RoomService;

@Component
public class RatingsUpdater {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RoomService roomService;
	
	private final String ratingsUrl = "https://us-central1-xmlprojekat.cloudfunctions.net/app/rating/";

	@Scheduled(cron = "0 0/15 * * * ?")
	public void updateRating() {
		System.out.println("Getting ratings");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<RatingDTO>> entity = new HttpEntity<List<RatingDTO>>(new ArrayList<RatingDTO>(), headers);
				
		System.out.println("Getting ratings for time " + this.getDate());
		System.out.println(this.ratingsUrl + this.getDate());
		List<RatingDTO> ratings = restTemplate.exchange(this.ratingsUrl + this.getDate(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<RatingDTO>>() {
		}).getBody();
		
		for(RatingDTO rating: ratings) {
			try {
				Room room = this.roomService.getRoom(rating.getRoomId());
				room.setNumberOfRatings(rating.getNumberOfRatings());
				room.setTotalRating(rating.getTotalRating());
				this.roomService.save(room);
			} catch (NotFoundException e) {
				System.out.println("Room not found " + rating.getRoomId());
			}
		}
	}
	
	private DateTime getDate() {
		DateTime dateTime = new DateTime().minusMinutes(15);
		return dateTime;
	}
	
}
