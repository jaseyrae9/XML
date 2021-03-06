package rs.ac.uns.ftn.xml.team17.adminservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.adminservice.dto.soap.AdditionalService.GetAdditionalServicesResponse;
import rs.ac.uns.ftn.xml.team17.adminservice.exception.NotFoundException;
import rs.ac.uns.ftn.xml.team17.adminservice.model.additionalService.AdditionalService;
import rs.ac.uns.ftn.xml.team17.adminservice.repository.AdditionalServiceRepository;

@Service
public class AdditionalServiceService {
	@Autowired
	private AdditionalServiceRepository additionalServiceRepository;

	public AdditionalService getAdditionalService(Integer id) throws NotFoundException {
		Optional<AdditionalService> additionalService = additionalServiceRepository.findById(id);
		if (!additionalService.isPresent()) {
			throw new NotFoundException(id, AdditionalService.class.getSimpleName());
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

	/**
	 * Deactivates the existing additional service.
	 * 
	 * @param id - id of the selected additional service
	 * @throws NotFoundException 
	 */
	public AdditionalService deleteAdditionalService(Integer id) throws NotFoundException {
		AdditionalService additionalService = this.getAdditionalService(id);
		additionalService.setActive(false);
		additionalServiceRepository.save(additionalService);
		return additionalService;
	}

	/**
	 * @return informations about all active additional services
	 */
	public Iterable<AdditionalService> getServices() {
		Iterable<AdditionalService> additionalServices = additionalServiceRepository.findAll();
		return additionalServices;
	}
	
	/**
	 * Converts additional services to SOAP DTO.
	 * 
	 * @return informations about all additional services
	 */
	public List<GetAdditionalServicesResponse.AdditionalService> getServicesSoap() {
		Iterable<AdditionalService> additionalServices = additionalServiceRepository.findAll();

		// convert services to DTO
		List<GetAdditionalServicesResponse.AdditionalService> ret = new ArrayList<>();
		for (AdditionalService service : additionalServices) {
			ret.add(new GetAdditionalServicesResponse.AdditionalService(service));
		}
		return ret;
	}
	

	/**
	 * Edits the existing additional service.
	 * 
	 * @param additionalServiceDTO - contains new informations for additional service
	 * @return updated additional service
	 * @throws NotFoundException 
	 */
	public AdditionalService editAddtionalService(Integer id, AdditionalService additionalServiceDTO) throws NotFoundException {
		AdditionalService additionalService = this.getAdditionalService(id);
		additionalService.setName(additionalServiceDTO.getName());
		return save(additionalService);
	}
}
