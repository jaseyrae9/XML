package rs.ac.uns.ftn.xml.team17.adminservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import isa.project.exception_handlers.RequestDataException;
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
	
	public AdditionalService save(AdditionalService additionalService) {
		return additionalServiceRepository.save(additionalService);
	}
	
	public Optional<AdditionalService> findAdditionalService(Integer id) {
		return additionalServiceRepository.findById(id);
	}

	public void deleteAdditionalService(Integer id) {
		Optional<AdditionalService> opt = findAdditionalService(id);
		
		if(!opt.isPresent()) {
			// TODO: exception
		}
		
		AdditionalService additionalService = opt.get();
		if (!additionalService.getActive()) {
			// TODO: exception
		}
		additionalService.setActive(false);
		
		additionalServiceRepository.save(additionalService);
	}
}
