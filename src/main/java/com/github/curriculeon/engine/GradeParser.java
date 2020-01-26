package com.github.curriculeon.engine;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetFile;
import com.github.curriculeon.utils.ResourceUtils;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.util.List;

/**
 * @author leonhunter
 * @created 01/24/2020 - 9:58 PM
 */
public class GradeParser {
    private final ExcelSpreadSheetFile excelSpreadSheetFileSource;
    private final ExcelSpreadSheetFile excelSpreadSheetFileDestination;
    private CSVSanitizer csvSanitizer;

    public GradeParser(ExcelSpreadSheetFile excelSource, CSVSanitizer csvSanitizer) {
        this.csvSanitizer = csvSanitizer;
        this.excelSpreadSheetFileSource = excelSource;
        this.excelSpreadSheetFileDestination = excelSource.copyTo(new File(new StringBuilder()
                .append(ResourceUtils.getResourceDirectoryPath())
                .append("/")
                .append("java-developer-philly-rubric-template_")
                .append(System.nanoTime())
                .append(".xlsx")
                .toString()));
    }

    public void parseToExcel() {
        ExcelSpreadSheet newSheet = excelSpreadSheetFileDestination.getNewSpreadSheet("Grades Parsed From Canvas");
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
