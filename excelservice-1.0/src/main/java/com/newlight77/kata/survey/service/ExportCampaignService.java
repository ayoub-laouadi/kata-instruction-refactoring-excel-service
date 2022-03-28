package com.newlight77.kata.survey.service;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;

import com.newlight77.kata.survey.exception.ExportCampaignException;
import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;

public interface ExportCampaignService {
	    Survey  createSurvey(Survey survey);
	    Survey getSurvey(String id);
	    Campaign createCampaign(Campaign campaign);
	    Campaign getCampaign(String id);
	    void sendResults(Campaign campaign, Survey survey) throws ExportCampaignException, IOException;
	    void writeFileAndSend(Survey survey, Workbook workbook) throws ExportCampaignException, IOException;
}
