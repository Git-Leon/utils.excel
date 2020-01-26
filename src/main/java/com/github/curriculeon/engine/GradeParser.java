package com.github.curriculeon.engine;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBook;
import com.github.curriculeon.utils.ResourceUtils;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
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
        excelSpreadSheetWorkBookDestination.addSheets(excelSpreadSheetWorkBookSource.getSheetsFromWorkBook());
        ExcelSpreadSheet newSheet = excelSpreadSheetWorkBookDestination.getExcelSpreadSheetByName(newSheetName).get();

        List<List<String>> rows = csvSanitizer.getRows();
        for (int i = 0; i < rows.size(); i++) {
            List<String> row = rows.get(i);
            for (int j = 0; j < row.size(); j++) {
                int rowNumber = i+1;
                int columnNumber = j+1;
                String cellValue = row.get(j);
                Cell cell = newSheet.getCell(rowNumber, columnNumber);
                cell.setCellValue(cellValue);
            }
        }
    }

}
