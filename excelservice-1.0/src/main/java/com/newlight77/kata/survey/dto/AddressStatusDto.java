package com.newlight77.kata.survey.dto;

import com.newlight77.kata.survey.model.Address;
import com.newlight77.kata.survey.model.Status;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressStatusDto {
    private String id;
    private Address address;
    private Status status;
}
