package rs.ac.uns.ftn.xml.team17.authservice.service.agentservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.xml.team17.authservice.model.entity.user.Agent;
import rs.ac.uns.ftn.xml.team17.authservice.repository.AgentRepository;

@Service
public class AgentService {

	@Autowired
	private AgentRepository agentRepository;
	
	public Agent getAgent(Integer id) {
		Optional<Agent> agent = agentRepository.findById(id);
		
		if(!agent.isPresent()) {
			// TODO: exception
		}
		
		return agent.get();
	}
	
	public Iterable<Agent> findAll() {
		return agentRepository.findAll();
	}
	
	public Agent save(Agent agent) {
		return agentRepository.save(agent);
	}
	
	public Optional<Agent> findAgent(Integer id) {
		return agentRepository.findById(id);
	}
}
