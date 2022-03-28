package com.newlight77.kata.survey.mapper;

import org.mapstruct.Mapper;

import com.newlight77.kata.survey.dto.AddressStatusDto;
import com.newlight77.kata.survey.model.AddressStatus;

@Mapper(componentModel="spring")
public interface AddressStatusMapper {
	
	AddressStatusDto toAddressStatusDto(AddressStatus addressStatus);
	AddressStatus toAddressStatus(AddressStatusDto addressStatusDto);
}
