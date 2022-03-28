package com.newlight77.kata.survey.excel;

import com.newlight77.kata.survey.model.AddressStatus;
import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {


    private Campaign campaign;
    private Survey survey;
    private Workbook workbook;
    private Sheet sheet;
    private CellStyle style;

    public ExcelWriter(Campaign campaign, Survey survey) {
        this.campaign = campaign;
        this.survey = survey;
        workbook = new XSSFWorkbook();
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public CellStyle getStyle() {
        return style;
    }

    public ExcelWriter build() {

      createSheet();
      writeHeaderLine();
      CellStyle titleStyle = setCellStyle();
      style = workbook.createCellStyle();
      style.setWrapText(true);
      setClientSection(titleStyle);
      writeSurveyLabelRow();
      writeCampaignRows(campaign, sheet, style);
      return this;
    }

  private void createSheet() {
    sheet = workbook.createSheet(ExcelConstants.SHEET_NAME);
    sheet.setColumnWidth(0, 10500);
    for (int i = 1; i <= 18; i++) {
        sheet.setColumnWidth(i, 6000);
    }
  }

  private void writeSurveyLabelRow() {
    Row surveyLabelRow = sheet.createRow(8);
    Cell surveyLabelCell = surveyLabelRow.createCell(0);
    surveyLabelCell.setCellValue(ExcelConstants.STREET_NUMBER);
    surveyLabelCell.setCellStyle(style);

    surveyLabelCell = surveyLabelRow.createCell(1);
    surveyLabelCell.setCellValue(ExcelConstants.STREET);
    surveyLabelCell.setCellStyle(style);

    surveyLabelCell = surveyLabelRow.createCell(2);
    surveyLabelCell.setCellValue(ExcelConstants.POSTAL_CODE);
    surveyLabelCell.setCellStyle(style);

    surveyLabelCell = surveyLabelRow.createCell(3);
    surveyLabelCell.setCellValue(ExcelConstants.CITY);
    surveyLabelCell.setCellStyle(style);

    surveyLabelCell = surveyLabelRow.createCell(4);
    surveyLabelCell.setCellValue(ExcelConstants.STATUS);
    surveyLabelCell.setCellStyle(style);
  }

  private void setClientSection(CellStyle titleStyle) {
        Row row = sheet.createRow(2);
        Cell cell = row.createCell(0);
        cell.setCellValue(ExcelConstants.CLIENT);
        cell.setCellStyle(titleStyle);

        Row clientRow = sheet.createRow(3);
        Cell nomClientRowLabel = clientRow.createCell(0);
        nomClientRowLabel.setCellValue(survey.getClient());
        nomClientRowLabel.setCellStyle(style);

        String clientAddress = survey.getClientAddress().getStreetNumber() + " "
                + survey.getClientAddress().getStreetName() + survey.getClientAddress().getPostalCode() + " "
                + survey.getClientAddress().getCity();

        Row clientAddressLabelRow = sheet.createRow(4);
        Cell clientAddressCell = clientAddressLabelRow.createCell(0);
        clientAddressCell.setCellValue(clientAddress);
        clientAddressCell.setCellStyle(style);

        row = sheet.createRow(6);
        cell = row.createCell(0);
        cell.setCellValue(ExcelConstants.NUMBER_OF_SURVEYS);
        cell = row.createCell(1);
        cell.setCellValue(campaign.getAddressStatuses().size());
    }

    private CellStyle setCellStyle() {
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont titleFont = ((XSSFWorkbook) workbook).createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setUnderline(FontUnderline.SINGLE);
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

    private void writeHeaderLine() {
        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName(ExcelConstants.ARIAL);
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setWrapText(false);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue(ExcelConstants.SHEET_NAME);
        headerCell.setCellStyle(headerStyle);
    }

    private void writeCampaignRows(Campaign campaign, Sheet sheet, CellStyle style) {
        int startIndex = 9;
        int currentIndex = 0;

        for (AddressStatus addressStatus : campaign.getAddressStatuses()) {

            Row surveyRow = sheet.createRow(startIndex + currentIndex);
            Cell surveyRowCell = surveyRow.createCell(0);
            surveyRowCell.setCellValue(addressStatus.getAddress().getStreetNumber());
            surveyRowCell.setCellStyle(style);

            surveyRowCell = surveyRow.createCell(1);
            surveyRowCell.setCellValue(addressStatus.getAddress().getStreetName());
            surveyRowCell.setCellStyle(style);

            surveyRowCell = surveyRow.createCell(2);
            surveyRowCell.setCellValue(addressStatus.getAddress().getPostalCode());
            surveyRowCell.setCellStyle(style);

            surveyRowCell = surveyRow.createCell(3);
            surveyRowCell.setCellValue(addressStatus.getAddress().getCity());
            surveyRowCell.setCellStyle(style);

            surveyRowCell = surveyRow.createCell(4);
            surveyRowCell.setCellValue(addressStatus.getStatus().toString());
            surveyRowCell.setCellStyle(style);

            currentIndex++;

        }
    }
}