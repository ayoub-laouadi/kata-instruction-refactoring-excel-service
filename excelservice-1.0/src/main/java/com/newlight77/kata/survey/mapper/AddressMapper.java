package com.newlight77.kata.survey.mapper;

import org.mapstruct.Mapper;

import com.newlight77.kata.survey.dto.AddressDto;
import com.newlight77.kata.survey.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	AddressDto toAddressDto(Address address);
    Address toAddress(AddressDto addressDto);

}
