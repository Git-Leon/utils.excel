package com.github.curriculeon.engine;

import com.github.curriculeon.engine.csv.CsvParser;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBookFile;

import java.io.File;


public class CsvToExcelGradesConverter {
    private File source;
    private File destination;

    public CsvToExcelGradesConverter(File csvSource, File csvDestination) {
        this.source = csvSource;
        this.destination = csvDestination;
    }

    public ExcelSpreadSheetWorkBookFile parseToExcel(File excelSpreadSheetFileToParse) {
        CsvParser csvParser = new CsvParser(source, destination);
        ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBookFile(excelSpreadSheetFileToParse);
        GradeParser gradeParser = new GradeParser(excelSpreadSheetWorkBook, csvParser);
        gradeParser.parseToExcel();
        return gradeParser.getExcelSpreadSheetWorkBookDestination();
    }
}
