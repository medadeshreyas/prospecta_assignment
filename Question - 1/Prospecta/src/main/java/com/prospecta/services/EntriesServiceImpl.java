package com.prospecta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospecta.exceptions.InvalidEntryException;
import com.prospecta.models.Entries;
import com.prospecta.repositories.EntriesRepository;

@Service
public class EntriesServiceImpl implements EntriesService {

	@Autowired
	private EntriesRepository entriesRepo;

	@Override
	public String saveEntry(Entries entry) throws InvalidEntryException {
		Entries en = entriesRepo.findByApi(entry.getApi())
				.orElseThrow(() -> new InvalidEntryException("Invalid Entry,Please try again"));

		entriesRepo.save(entry);
		return "Your Request Was Succesfull";

	}

	@Override
	public List<Entries> getAllEntries() {
		return entriesRepo.findAll();
	}

}
