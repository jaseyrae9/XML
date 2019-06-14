package rs.ac.uns.ftn.xml.team17.reservationsservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.reservationsservice.model.recension.Recension;
import rs.ac.uns.ftn.xml.team17.reservationsservice.repository.RecensionRepository;

@Service
public class RecensionService {

	@Autowired
	private RecensionRepository recensionRepository;
	
	public List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension> getRecensions(Date date) {
		System.out.println("RecensionService getRecensions");
		System.out.println(date);
		
		List<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension> ret = new ArrayList<rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension>();
		
		List<Recension> recensions = recensionRepository.findAllByModificationDateAfter(date);
		
		for(Recension recension : recensions) {
			rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension r = new rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension(recension);
			ret.add(r);
		}
		
		return ret;
		
	}

}
