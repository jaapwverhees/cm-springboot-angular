package com.verhees.cm.repository;

import com.verhees.cm.model.game.Game;
import com.verhees.cm.model.score.Score;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
