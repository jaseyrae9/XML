package rs.ac.uns.ftn.xml.team17.reservationsservice.controller.occupancy;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.occupancy.OccupancyRequest;
import rs.ac.uns.ftn.xml.team17.reservationsservice.dto.soap.occupancy.OccupancyResponse;

@Endpoint
public class OccupancyEndpoint {

	
	// TODO: dodati u config
	@PayloadRoot(namespace = "http://www.team17.xml.ftn.uns.ac.rs/Occupancy", localPart = "occupancyRequest")
    @ResponsePayload
    public OccupancyResponse occupancy(@RequestPayload OccupancyRequest occupancyRequest) {
		OccupancyResponse response = new OccupancyResponse();

        return response;
    }
	
}
