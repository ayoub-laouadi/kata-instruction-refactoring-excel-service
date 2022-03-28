package com.newlight77.kata.survey.client;

import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;

public interface CampaignClient {

	void createSurvey(Survey survey);
	public Survey getSurvey(String id);
	public void createCampaign(Campaign campaign);
}
