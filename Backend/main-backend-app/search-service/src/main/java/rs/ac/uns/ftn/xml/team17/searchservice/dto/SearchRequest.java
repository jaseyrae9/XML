package rs.ac.uns.ftn.xml.team17.searchservice.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SearchRequest {
	/*
	 * Osnovna pretraga
	 */
	@NotBlank(message = "Please, enter location you want to visit.")
	private String location;
	@NotNull(message = "Please, enter start date of your visit.")
	private Date start;
	@NotNull(message = "Please, enter end date of your visit.")
	private Date end;
	@Min(value = 1, message = "Minimal number of persons is 1.")
	@NotNull(message = "Please, enter number of people.")
	private Integer numberOfPeople;
	
	/*
	 * Dodatni parametri
	 */
	private Integer type;
	private Integer category;
	private List<Integer> additionalServices;
	@Min(value = 0, message = "Minimal distance from location is 0.")
	private Double distanceFromLocation;
	@Min(value = 0, message = "Minimal number of cancelation days is 0.")
	private Integer cancelationDays;
}
