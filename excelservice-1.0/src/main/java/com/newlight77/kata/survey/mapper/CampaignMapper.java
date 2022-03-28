package com.newlight77.kata.survey.mapper;

import org.mapstruct.Mapper;

import com.newlight77.kata.survey.dto.CampaignDto;
import com.newlight77.kata.survey.model.Campaign;

@Mapper(componentModel="spring")
public interface CampaignMapper {

	CampaignDto toCampaignDto(Campaign compaign);
	Campaign toCampaign(CampaignDto compaignDto);
}
