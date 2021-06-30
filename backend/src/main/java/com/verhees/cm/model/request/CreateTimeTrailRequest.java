package com.verhees.cm.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateTimeTrailRequest {
    private String name;
    private List<Date> stages;
    private List<String> teams;
}
