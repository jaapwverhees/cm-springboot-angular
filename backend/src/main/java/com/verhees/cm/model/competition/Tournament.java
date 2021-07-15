package com.verhees.cm.model.competition;

import com.verhees.cm.model.game.Game;
import com.verhees.cm.model.stage.TimeTrialStage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@Table(name = "tournament")
public class Tournament extends Competition {

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Game> games;

    private Date maxDate;
}
