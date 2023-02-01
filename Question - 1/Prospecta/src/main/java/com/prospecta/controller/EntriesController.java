package com.prospecta.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prospecta.exceptions.InvalidEntryException;
import com.prospecta.models.Data;
import com.prospecta.models.Entries;
import com.prospecta.models.EntriesDTO;
import com.prospecta.services.EntriesService;

@RestController
public class EntriesController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EntriesService eService;

	// This API displays all titles and description of the present entries

	// http:localhost:8080/entries

	@GetMapping("/entries")
	public ResponseEntity<List<EntriesDTO>> getEntriesHandler() {
		Data data = restTemplate.getForObject("https://api.publicapis.org/entries", Data.class);

		List<Entries> apiEntries = data.getEntries();

		List<EntriesDTO> totalresult = new ArrayList<>();

		for (Entries e : apiEntries) {
			EntriesDTO entriesDto = new EntriesDTO();
			entriesDto.setTitle(e.getApi());
			entriesDto.setDescription(e.getDescription());
			totalresult.add(entriesDto);
		}
		return new ResponseEntity<List<EntriesDTO>>(totalresult, HttpStatus.ACCEPTED);
	}

	// enter a category in the end as it is a path variable

	// example:-
	// http:localhost:8080/Animals

	@GetMapping("/entries/{category}")
	public ResponseEntity<List<EntriesDTO>> getEntriesByCategoryHandler(@PathVariable("category") String category) {

		Data data = restTemplate.getForObject("https://api.publicapis.org/entries", Data.class);
		List<Entries> entries = data.getEntries();

		List<EntriesDTO> ans = new ArrayList<>();

		for (Entries e : entries) {
			if (e.getCategory().equals(category)) {
				EntriesDTO entryDto = new EntriesDTO();
				entryDto.setTitle(e.getApi());
				entryDto.setDescription(e.getDescription());
				ans.add(entryDto);
			}
		}
		return new ResponseEntity<List<EntriesDTO>>(ans, HttpStatus.ACCEPTED);
	}

	// you have to post a Entries request body

	// http:localhost:8080/entries

	@PostMapping("/entries")
	public ResponseEntity<String> saveEntryHandler(@RequestBody Entries entry) throws InvalidEntryException {
		return new ResponseEntity<String>(eService.saveEntry(entry), HttpStatus.CREATED);
	}

}
