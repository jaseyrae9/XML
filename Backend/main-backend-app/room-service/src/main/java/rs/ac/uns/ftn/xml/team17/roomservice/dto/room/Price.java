package rs.ac.uns.ftn.xml.team17.roomservice.dto.room;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Price {
	private Date date;
	private Double amount;
}
