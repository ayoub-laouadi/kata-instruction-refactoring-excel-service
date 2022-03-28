package com.newlight77.kata.survey.mapper;

import org.mapstruct.Mapper;

import com.newlight77.kata.survey.dto.QuestionDto;
import com.newlight77.kata.survey.model.Question;

@Mapper(componentModel="spring")
public interface QuestionMapper {

	QuestionDto toQuestionDto(Question question);
	Question toQuestion(QuestionDto questionDto);
}
