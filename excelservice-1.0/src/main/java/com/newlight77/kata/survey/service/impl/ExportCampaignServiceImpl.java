package com.newlight77.kata.survey.service.impl;

import com.newlight77.kata.survey.model.AddressStatus;
import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;
import com.newlight77.kata.survey.client.impl.CampaignClientImpl;
import com.newlight77.kata.survey.excel.ExcelWriter;
import com.newlight77.kata.survey.exception.ExportCampaignException;
import com.newlight77.kata.survey.service.ExportCampaignService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ExportCampaignServiceImpl implements ExportCampaignService {

  private static final Logger logger = LoggerFactory.getLogger(ExportCampaignServiceImpl.class);
  private CampaignClientImpl campaignWebService;
  private MailServiceImpl mailServiceImpl;
  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  
  public ExportCampaignServiceImpl(final CampaignClientImpl campaignWebService, MailServiceImpl mailServiceImpl) {
    this.campaignWebService = campaignWebService;
    this.mailServiceImpl = mailServiceImpl;
  }

  @Override
  public Survey createSurvey(Survey survey) {
    return campaignWebService.createSurvey(survey);
  }
  @Override
  public Survey getSurvey(String id) {
    return campaignWebService.getSurvey(id);
  }
  @Override
  public Campaign createCampaign(Campaign campaign) {
    return campaignWebService.createCampaign(campaign);
  }
  @Override
  public Campaign getCampaign(String id) {
    return campaignWebService.getCampaign(id);
  }
  @Override
  public void sendResults(Campaign campaign, Survey survey) throws ExportCampaignException, IOException {
	    ExcelWriter excelWriter = new ExcelWriter(campaign, survey).build();
	    Workbook workbook = excelWriter.getWorkbook();
	    writeFileAndSend(survey, workbook);
	  }

  public void writeFileAndSend(Survey survey, Workbook workbook) throws ExportCampaignException, IOException {
	    try {
	      File resultFile = new File(System.getProperty("java.io.tmpdir"), "survey-" + survey.getId() + "-" + dateTimeFormatter.format(LocalDate.now()) + ".xlsx");
	      FileOutputStream outputStream = new FileOutputStream(resultFile);
	      workbook.write(outputStream);

	      mailServiceImpl.send(resultFile);
	      resultFile.deleteOnExit();
	    } catch (FileNotFoundException exception) {
	      logger.error(exception.getMessage());
	    } catch (IOException exception) {
	      logger.error(exception.getMessage());
	    } catch(Exception e) {
	        throw new ExportCampaignException("Error while trying to send email", e);
	    } finally {
	        workbook.close();
	    }
	  }
  

}
