package com.verhees.cm.model.competition;

import com.verhees.cm.model.stage.KnockoutStage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@Table(name = "knockout")
public class Knockout extends Competition {

    @OrderColumn(name="STAGE_INDEX")
    @OneToMany(cascade = CascadeType.ALL)
    private List<KnockoutStage> stages;

    private Date maxDate;
}
