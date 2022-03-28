package com.newlight77.kata.survey.dto;

import lombok.Data;

import java.util.List;

import com.newlight77.kata.survey.model.Address;
import com.newlight77.kata.survey.model.Question;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDto {
    private String id;
    private String sommary;
    private String client;
    private Address clientAddress;
    private List<Question> questions;
}

