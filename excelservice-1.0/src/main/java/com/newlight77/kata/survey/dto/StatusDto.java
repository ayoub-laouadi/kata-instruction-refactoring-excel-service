package com.newlight77.kata.survey.dto;

import com.newlight77.kata.survey.model.Status;

public enum StatusDto {
    TODO,
    DONE
    ;

    public String value() {
        return name();
    }

    public static StatusDto fromValue(String v) {
        return valueOf(v);
    }
}
