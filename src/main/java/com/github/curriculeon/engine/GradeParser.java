package com.github.curriculeon.engine;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.utils.ResourceUtils;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;

/**
 * @author leonhunter
 * @created 01/24/2020 - 9:58 PM
 */
public class GradeParser {
    private final ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBookDestination;
    private CSVSanitizer csvSanitizer;

    public GradeParser(ExcelSpreadSheetWorkBookFile excelSource, CSVSanitizer csvSanitizer) {
        this.csvSanitizer = csvSanitizer;
        this.excelSpreadSheetWorkBookDestination = excelSource.copyTo(new File(new StringBuilder()
                .append(ResourceUtils.getResourceDirectoryPath())
                .append("/")
                .append("java-developer-philly-rubric-template_")
                .append(System.nanoTime())
                .append(".xlsx")
                .toString()));
    }

    public void parseToExcel() {
        String newSheetName = "Grades Parsed From Canvas";
        ExcelSpreadSheet newExcelSpreadSheet = excelSpreadSheetWorkBookDestination.createExcelSpreadSheetByName(newSheetName);
        Sheet newSheet = newExcelSpreadSheet.getSheet();
        csvSanitizer.parseToSheet(newSheet);
        excelSpreadSheetWorkBookDestination.addSheet(newSheet);
        excelSpreadSheetWorkBookDestination.setSheetOrder(newSheetName, 0);
        excelSpreadSheetWorkBookDestination.setActive(newSheet);
        excelSpreadSheetWorkBookDestination.flush();
    }

    public ExcelSpreadSheetWorkBookFile getExcelSpreadSheetWorkBookDestination() {
        return excelSpreadSheetWorkBookDestination;
    }
}