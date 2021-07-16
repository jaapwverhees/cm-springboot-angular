package com.verhees.cm.model.response;

import com.verhees.cm.model.competition.Knockout;
import com.verhees.cm.model.competition.TimeTrail;
import com.verhees.cm.model.competition.Tournament;
import lombok.*;

import static com.verhees.cm.model.enums.CompetionType.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionResponse {
    private String name;
    private String type;

    public static CompetitionResponse of(TimeTrail timeTrail){
        return CompetitionResponse.builder()
                .name(timeTrail.getName())
                .type(TIMETRAIL.type)
                .build();
    }

    public static CompetitionResponse of(Tournament tournament){
        return CompetitionResponse.builder()
                .name(tournament.getName())
                .type(TOURNAMENT.type)
                .build();
    }

    public static CompetitionResponse of(Knockout knockout){
        return CompetitionResponse.builder()
                .name(knockout.getName())
                .type(KNOCKOUT.type)
                .build();
    }
}
