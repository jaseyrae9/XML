package rs.ac.uns.ftn.xml.team17.adminservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.adminservice.model.additionalService.AdditionalService;
import rs.ac.uns.ftn.xml.team17.adminservice.repository.AdditionalServiceRepository;

@Service
public class AdditionalServiceService {
	@Autowired
	private AdditionalServiceRepository additionalServiceRepository;
	
	public AdditionalService getAdditionalService(Integer id) {
		Optional<AdditionalService> additionalService = additionalServiceRepository.findById(id);
		if(!additionalService.isPresent()) {
			//TODO: Ovde baciti exception
		}
		return additionalService.get();
	}
	
	public Iterable<AdditionalService> findAll() {
		return additionalServiceRepository.findAll();
	} 
}
