package com.github.curriculeon.engine;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBook;
import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.utils.ResourceUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leonhunter
 * @created 01/24/2020 - 9:58 PM
 */
public class GradeParser {
    private final ExcelSpreadSheetWorkBook excelSpreadSheetWorkBookSource;
    private final ExcelSpreadSheetWorkBook excelSpreadSheetWorkBookDestination;
    private CSVSanitizer csvSanitizer;

    public GradeParser(ExcelSpreadSheetWorkBook excelSource, CSVSanitizer csvSanitizer) {
        this.csvSanitizer = csvSanitizer;
        this.excelSpreadSheetWorkBookSource = excelSource;
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
        ExcelSpreadSheet newExcelSpreadSheet = excelSpreadSheetWorkBookDestination.getExcelSpreadSheetByName(newSheetName);
        Sheet newSheet = newExcelSpreadSheet.getSheet();
        csvSanitizer.parseToSheet(newSheet);
        excelSpreadSheetWorkBookDestination.addSheet(newSheet);
        excelSpreadSheetWorkBookDestination.setActive(newSheet);
        excelSpreadSheetWorkBookDestination.flush();
    }

}
