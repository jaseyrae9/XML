package rs.ac.uns.ftn.xml.team17.adminservice.controller.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.adminservice.dto.soap.AdditionalService.GetAdditionalServicesRequest;
import rs.ac.uns.ftn.xml.team17.adminservice.dto.soap.AdditionalService.GetAdditionalServicesResponse;
import rs.ac.uns.ftn.xml.team17.adminservice.dto.soap.AdditionalService.GetAdditionalServicesResponse.AdditionalService;
import rs.ac.uns.ftn.xml.team17.adminservice.service.AdditionalServiceService;

@Endpoint
public class AdditionalServiceEndpoint {

	@Autowired
	private AdditionalServiceService additionalServiceService;
													
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/AdditionalServices", localPart = "getAdditionalServicesRequest")
    @ResponsePayload
    public GetAdditionalServicesResponse getCountry(@RequestPayload GetAdditionalServicesRequest getAdditionalServicesRequest) {
		GetAdditionalServicesResponse response = new GetAdditionalServicesResponse();
		List<AdditionalService> ret = additionalServiceService.getServicesSoap();
		response.setAdditionalService(ret);
        return response;
    }
}
