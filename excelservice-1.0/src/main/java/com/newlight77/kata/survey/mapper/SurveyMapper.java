package com.newlight77.kata.survey.mapper;

import org.mapstruct.Mapper;

import com.newlight77.kata.survey.dto.SurveyDto;
import com.newlight77.kata.survey.model.Survey;

@Mapper(componentModel="Spring")
public interface SurveyMapper {

	SurveyDto toSurveyDto(Survey survey);
	Survey toSurvey(SurveyDto surveyDto);
	
}
