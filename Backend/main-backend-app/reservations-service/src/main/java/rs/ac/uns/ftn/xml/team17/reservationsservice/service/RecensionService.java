package rs.ac.uns.ftn.xml.team17.reservationsservice.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.recension.RecensionResponseDTO;

@Service
public class RecensionService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension> getRecensions(Date date, Integer hotelId) {
		System.out.println("RecensionService getRecensions");
		System.out.println(date.toString());
		
		List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension> ret = new ArrayList<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension>();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		List<RecensionResponseDTO> recensions = new ArrayList<RecensionResponseDTO>();
		HttpEntity<Object> entity = new HttpEntity<Object>(recensions, headers);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        String strDate = dateFormat.format(date);  
		strDate = strDate.replace(" ", "T");
		strDate += "Z";
		
		System.out.println("https://us-central1-xmlprojekat.cloudfunctions.net/app/recension/" + hotelId + "/" + strDate);
		List<RecensionResponseDTO> retRecensions = restTemplate.exchange("https://us-central1-xmlprojekat.cloudfunctions.net/app/recension/" + hotelId + "/" + strDate,
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<RecensionResponseDTO>>() {
				}).getBody();
	
		System.out.println("Povukao sve recenzije, sada ide konverzija");
		for(RecensionResponseDTO recension : retRecensions) {
			rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension r = new rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension(recension);
			ret.add(r);
		}
		
		return ret;
		
	}

}
