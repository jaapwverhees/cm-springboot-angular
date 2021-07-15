package com.verhees.cm.model.request;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateKnockoutRequest {
    private String name;
    private Date maxDate;
    private ArrayList<GameRequest> matches;
}
