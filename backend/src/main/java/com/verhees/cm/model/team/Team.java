package com.verhees.cm.model.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.verhees.cm.model.stage.TimeTrialStage;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "teams")
    private Set<TimeTrialStage> timeTrailStage;
}
