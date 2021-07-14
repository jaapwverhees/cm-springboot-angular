package com.verhees.cm.repository;

import com.verhees.cm.model.competition.Tournament;
import org.springframework.data.repository.CrudRepository;

public interface TournamentRepository extends CrudRepository<Tournament, String> {
}
