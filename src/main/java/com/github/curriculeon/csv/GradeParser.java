package com.github.curriculeon.csv;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetFile;
import org.apache.poi.ss.usermodel.Cell;

import java.util.List;

public class GradeParser {
    private final ExcelSpreadSheetFile excelSpreadSheetFile;
    private CSVParser csvParser;

    public GradeParser(ExcelSpreadSheetFile excelSpreadSheetFile, CSVParser csvParser) {
        this.excelSpreadSheetFile = excelSpreadSheetFile;
        this.csvParser = csvParser;
    }

    public void parseToExcel() {
        ExcelSpreadSheet newSheet = excelSpreadSheetFile.getNewSpreadSheet("Grades Parsed From Canvas");
        List<List<String>> rows = csvParser.getRows();
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
