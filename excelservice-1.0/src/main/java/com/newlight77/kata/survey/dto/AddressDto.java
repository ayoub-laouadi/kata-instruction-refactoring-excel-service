package com.newlight77.kata.survey.dto;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String id;
    private String streetNumber;
    private String streetName;
    private String postalCode;
    private String city;
}
