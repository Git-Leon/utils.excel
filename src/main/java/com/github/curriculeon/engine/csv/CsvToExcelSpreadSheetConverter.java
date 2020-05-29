package com.github.curriculeon.engine.csv;

import com.github.curriculeon.engine.csv.CsvParser;
import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBookFile;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;

/**
 * @author leonhunter
 * @created 01/24/2020 - 9:58 PM
 */
public class CsvToExcelSpreadSheetConverter {
    private final CsvParser csvParser;
    private final ExcelSpreadSheetWorkBookFile excelSource;
    private ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBookDestination;

    public CsvToExcelSpreadSheetConverter(ExcelSpreadSheetWorkBookFile excelSource, File excelFileDestination, CsvParser csvParser) {
        this.csvParser = csvParser;
        this.excelSource = excelSource;
        this.excelSpreadSheetWorkBookDestination = excelSource.copyTo(excelFileDestination);
    }

    public void parseToExcel(String newSheetName) {
        ExcelSpreadSheet newExcelSpreadSheet = excelSpreadSheetWorkBookDestination.createExcelSpreadSheetByName(newSheetName);
        Sheet newSheet = newExcelSpreadSheet.getSheet();
        csvParser.parseToSheet(newSheet);
        excelSpreadSheetWorkBookDestination.addSheet(newSheet);
        excelSpreadSheetWorkBookDestination.setSheetOrder(newSheetName, 0);
        excelSpreadSheetWorkBookDestination.setActive(newSheet);
        excelSpreadSheetWorkBookDestination.flush();
        excelSpreadSheetWorkBookDestination.close();
    }

    public ExcelSpreadSheetWorkBookFile getExcelSpreadSheetWorkBookDestination() {
        return excelSpreadSheetWorkBookDestination;
    }
}
