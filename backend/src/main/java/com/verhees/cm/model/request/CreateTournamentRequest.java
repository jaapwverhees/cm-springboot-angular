package com.verhees.cm.model.request;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTournamentRequest {
    private String name;
    private Set<String> teams;
}
