package com.verhees.cm.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameRequest {
    private String teamOne;
    private String teamTwo;
}
