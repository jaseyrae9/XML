package rs.ac.uns.ftn.xml.team17.searchservice.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.searchservice.dto.RoomPreview;
import rs.ac.uns.ftn.xml.team17.searchservice.dto.SearchRequest;

@RestController
public class SearchController {
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<Page<RoomPreview>> searchRooms(@RequestBody @Valid SearchRequest searchRequest, Pageable page){
		return null;
	}
}
