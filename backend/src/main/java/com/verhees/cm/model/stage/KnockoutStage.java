package com.verhees.cm.model.stage;

import com.verhees.cm.model.game.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@Table(name = "knockout_stage")
public class KnockoutStage extends Stage {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Game> games;

    int stageIndex;
}
