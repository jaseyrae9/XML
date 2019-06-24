package rs.ac.uns.ftn.xml.team17.searchservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.xml.team17.searchservice.dto.RoomPreview;
import rs.ac.uns.ftn.xml.team17.searchservice.dto.SearchRequest;
import rs.ac.uns.ftn.xml.team17.searchservice.service.SearchService;

@RestController
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<List<RoomPreview>> searchRooms(@RequestBody @Valid SearchRequest searchRequest){
		List<RoomPreview> res = searchService.search(searchRequest);
		return new ResponseEntity<List<RoomPreview>>(res, HttpStatus.OK);
	}
}
