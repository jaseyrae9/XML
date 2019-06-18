package rs.ac.uns.ftn.xml.team17.reservationsservice.controller.recension;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.GetRecensionsRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.GetRecensionsResponse;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.getrecensions.Recension;
import rs.ac.uns.ftn.xml.team17.reservationsservice.service.RecensionService;

@Endpoint
public class RecensionEndpoint {
	
	@Autowired
	private RecensionService recensionService;
	
	@Autowired
	private HttpServletRequest request;
	
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/GetRecensions", localPart = "getRecensionsRequest")
    @ResponsePayload
    public GetRecensionsResponse getRecensions(@RequestPayload GetRecensionsRequest getRecensionsRequest) {
		GetRecensionsResponse response = new GetRecensionsResponse();
		Integer hotelId = Integer.parseInt(request.getHeader("Hotel"));
		List<Recension> ret = recensionService.getRecensions(getRecensionsRequest.getDate(), hotelId);
		response.setRecension(ret);
        return response;
    }

}
