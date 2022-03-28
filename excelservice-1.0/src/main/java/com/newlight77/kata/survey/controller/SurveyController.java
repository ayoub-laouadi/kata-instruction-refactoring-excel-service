package com.newlight77.kata.survey.controller;

import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;
import com.newlight77.kata.survey.service.ExportCampaignService;
import com.newlight77.kata.survey.controler.GetMapping;
import com.newlight77.kata.survey.controler.PathVariable;
import com.newlight77.kata.survey.controler.PostMapping;
import com.newlight77.kata.survey.controler.RequestBody;
import com.newlight77.kata.survey.controler.RequestParam;
import com.newlight77.kata.survey.dto.CampaignDto;
import com.newlight77.kata.survey.dto.SurveyDto;
import com.newlight77.kata.survey.mapper.CampaignMapper;
import com.newlight77.kata.survey.mapper.CampaignMapperImpl;
import com.newlight77.kata.survey.mapper.SurveyMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/survey/")
public class SurveyController {

	private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);
	
	private final ExportCampaignService exportCampaignService;
    private final SurveyMapper surveyMapper;
    private final CampaignMapper campaignMapper;

    public SurveyController(@Autowired final ExportCampaignService exportCampaignService,
                            @Autowired final SurveyMapper surveyMapper,
                            @Autowired final CampaignMapper CampaignMapper) {
        this.exportCampaignService = exportCampaignService;
        this.surveyMapper = surveyMapper;
        this.campaignMapper = campaignMapper;
    }
    

    @PostMapping
    public ResponseEntity<SurveyDto> createSurvey(@RequestBody SurveyDto surveyDto) {
    	 logger.info("POST: create survey");
         SurveyDto createdSurveyDto = surveyMapper.toSurveyDto(exportCampaignService.createSurvey(surveyMapper.toSurvey(surveyDto)));
         logger.info("POST: survey created");
        return ResponseEntity<>(createdSurveyDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyDto> getSurvey(@PathVariable String id) {
        logger.info("GET: create survey with id : {}", id);
        SurveyDto foundedSurveyDto = surveyMapper.toSurveyDto(exportCampaignService.getSurvey(id));
        return new ResponseEntity<>(foundedSurveyDto, HttpStatus.OK);
    }


    @PostMapping(value = "/campaign")
    public ResponseEntity<CampaignDto> createCampaign(@RequestBody CampaignDto campaignDto) {
        logger.info("POST: create campaign");
        CampaignDto createdCalCampaignDto = campaignMapper.toCampaignDto(exportCampaignService.createCampaign(campaignMapper.toCampaign(campaignDto)));
        logger.info("POST: campaign created");
        return new ResponseEntity<>(createdCalCampaignDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/campaign/{id}")
    public ResponseEntity<CampaignDto> getCampaign(@PathVariable String id) {
        logger.info("GET: get campaign with id : {}", id);
        CampaignDto foundedCampaignDto = campaignMapper.toCampaignDto( exportCampaignService.getCampaign(id));
        return new ResponseEntity<>(foundedCampaignDto, HttpStatus.OK);
    }

    @PostMapping(value = "/campaign/export")
    public void exportCampaign(@RequestParam String campaignId) {
        logger.info("POST: export campaign with id : {}", campaignId);
        Campaign campaign = exportCampaignService.getCampaign(campaignId);
        Survey survey = exportCampaignService.getSurvey(campaign.getSurveyId());
        exportCampaignService.sendResults(campaign, survey);

    }
}

