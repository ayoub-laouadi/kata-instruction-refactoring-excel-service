package com.newlight77.kata.survey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.newlight77.kata.survey.model.AddressStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDto {
    private String id ;
    private String surveyId;
    private List<AddressStatus> addressStatuses;
}
