package com.github.curriculeon.engine;

import com.github.curriculeon.engine.csv.CsvParser;
import com.github.curriculeon.engine.csv.CsvToExcelSpreadSheetConverter;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.utils.io.DirectoryReference;

/**
 * @author leonhunter
 * @created 01/24/2020 - 9:58 PM
 */
public class GradeParser {
    private final CsvToExcelSpreadSheetConverter csvToExcelSpreadSheetConverter;

    public GradeParser(ExcelSpreadSheetWorkBookFile excelSource, CsvParser csvParser) {
        this.csvToExcelSpreadSheetConverter = new CsvToExcelSpreadSheetConverter(excelSource, DirectoryReference.TARGETDIRECTORY
            .getFileFromDirectory(new StringBuilder()
                .append("PARSED-")
                .append("java-developer-philly-rubric-template_")
                .append(System.nanoTime())
                .append(".xlsx")
                .toString()), csvParser);
    }


    public void parseToExcel() {
        csvToExcelSpreadSheetConverter.parseToExcel("Grades Parsed From Canvas");
    }

    public ExcelSpreadSheetWorkBookFile getExcelSpreadSheetWorkBookDestination() {
        return csvToExcelSpreadSheetConverter.getExcelSpreadSheetWorkBookDestination();
    }
}
