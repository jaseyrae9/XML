package rs.ac.uns.ftn.xml.team17.reservationsservice.controller.recension;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recension")
public class RecensionController {

	@RequestMapping(value = "/{recensionId}", method = RequestMethod.PUT)
	public ResponseEntity<?> approveRecension(@PathVariable Integer recensionId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		//HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);

		//return restTemplate.exchange("http://localhost:8080/products/" + id, HttpMethod.PUT, entity, String.class)
		//		.getBody();
		return null;
	}

}
