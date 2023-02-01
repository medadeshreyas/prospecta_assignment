package com.prospecta.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prospecta.models.Entries;

public interface EntriesRepository extends JpaRepository<Entries, Integer> {

	public Optional<Entries> findByApi(String api);

	
}
