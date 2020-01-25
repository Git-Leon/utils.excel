package com.github.curriculeon.csv;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetFile;
import org.apache.poi.ss.usermodel.Cell;

import java.util.List;

public class GradeParser {
    private final ExcelSpreadSheetFile excelSpreadSheetFile;
    private CSVSanitizer csvSanitizer;

    public GradeParser(ExcelSpreadSheetFile excelSpreadSheetFile, CSVSanitizer csvSanitizer) {
        this.excelSpreadSheetFile = excelSpreadSheetFile;
        this.csvSanitizer = csvSanitizer;
    }

    public void parseToExcel() {
        ExcelSpreadSheet newSheet = excelSpreadSheetFile.getNewSpreadSheet("Grades Parsed From Canvas");
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
