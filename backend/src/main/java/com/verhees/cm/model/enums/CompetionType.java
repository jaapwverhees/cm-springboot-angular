package com.verhees.cm.model.enums;

public enum CompetionType {
    TIMETRAIL("TIMETRAIL"),
    TOURNAMENT("TOURNAMENT"),
    KNOCKOUT("KNOCKOUT");

    public String type;

    CompetionType(String type) {
        this.type = type;
    }
}
