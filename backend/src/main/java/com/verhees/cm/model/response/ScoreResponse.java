package com.verhees.cm.model.response;

import com.verhees.cm.model.team.Team;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreResponse {
    private Long id;

    private Team team;

    private Long score;
}
