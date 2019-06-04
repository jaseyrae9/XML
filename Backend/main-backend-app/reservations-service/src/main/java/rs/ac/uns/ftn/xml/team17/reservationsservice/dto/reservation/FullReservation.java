package rs.ac.uns.ftn.xml.team17.reservationsservice.dto.reservation;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.message.Message;

@NoArgsConstructor
@Getter
@Setter
public class FullReservation {
	private ReservationPreview reservationPreview;
	private List<Message> messages;
}
