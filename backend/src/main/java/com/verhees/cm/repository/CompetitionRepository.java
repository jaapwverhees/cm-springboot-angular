package com.verhees.cm.repository;

import com.verhees.cm.model.competition.Competition;
import org.springframework.data.repository.CrudRepository;

public interface CompetitionRepository extends CrudRepository<Competition, String> {
}
