package com.verhees.cm.repository;

import com.verhees.cm.model.team.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Integer> {
    Team findTeamByName(String name);
}
