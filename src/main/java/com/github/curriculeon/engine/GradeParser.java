package com.github.curriculeon.engine;

import com.github.curriculeon.engine.csv.CsvParser;
import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.utils.io.DirectoryReference;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author leonhunter
 * @created 01/24/2020 - 9:58 PM
 */
public class GradeParser {
    private ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBookDestination;
    private final CsvParser csvParser;
    private final ExcelSpreadSheetWorkBookFile excelSource;

    public GradeParser(ExcelSpreadSheetWorkBookFile excelSource, CsvParser csvParser) {
        this.csvParser = csvParser;
        this.excelSource = excelSource;
    }

    /**
     * Decouples construction from file-creation
     */
    private void init() {
        this.excelSpreadSheetWorkBookDestination = excelSource.copyTo(DirectoryReference.TARGETDIRECTORY
                .getFileFromDirectory(new StringBuilder()
                        .append("PARSED-")
                        .append("java-developer-philly-rubric-template_")
                        .append(System.nanoTime())
                        .append(".xlsx")
                        .toString()));
    }

    public void parseToExcel() {
        init();
        String newSheetName = "Grades Parsed From Canvas";
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