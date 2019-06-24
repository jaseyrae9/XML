package rs.ac.uns.ftn.xml.team17.roomservice.dto.room;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.roomservice.model.price.Price;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomPriceDTO {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date date;
	private Double amount;
	
	public RoomPriceDTO(Price price) {
		this.date = price.getDate();
		this.amount = price.getAmount();
	}
}
