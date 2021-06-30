package com.verhees.cm.repository;

import com.verhees.cm.model.score.Score;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score, Long> {
}
