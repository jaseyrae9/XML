package rs.ac.uns.ftn.xml.team17.searchservice.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.price.Price;
import rs.ac.uns.ftn.xml.team17.searchservice.model.entity.room.Room;
import rs.ac.uns.ftn.xml.team17.searchservice.repository.PriceRepository;

@Service
public class PriceService {
	@Autowired
	private PriceRepository priceRepository;
	
	/**
	 * For selected room gets all special prices.
	 * @param room - room id
	 * @param from - inclusive start date
	 * @param to - inclusive end date
	 * @return - list of prices
	 */
	public List<Price> getPrices(Integer room, Date from, Date to){
		return this.priceRepository.getPrices(room, from, to);
	}
	
	/**
	 * For given date decided what price should be applied. If there's price with selected date in 
	 * prices list, that price is used. Otherwise, default price is used.
	 * @param prices - list of special prices for a room
	 * @param defaultPrice - default room price
	 * @param date - date for which price is being calculated
	 * @return - calculated price
	 */
	public Double decidePrice(List<Price> prices, Double defaultPrice, Date date) {
		Optional<Price> price = prices.stream().filter(o -> o.getDate().equals(date)).findFirst();
		if(price.isPresent()) {
			return price.get().getAmount();
		}
		return defaultPrice;
	}
	
	/**
	 * For given room calculates prices for days between from and to.
	 * @param room
	 * @param from
	 * @param to
	 * @return
	 */
	public Double calculatePrice(Room room, Date from, Date to) {
		Double price = 0.0;
		List<Price> prices = this.getPrices(room.getId(), from, to);
		LocalDate start = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) {			
			Date datePrice = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			price += this.decidePrice(prices, room.getDefaultPrice(), datePrice);
		}	
		return price;
	}
}
