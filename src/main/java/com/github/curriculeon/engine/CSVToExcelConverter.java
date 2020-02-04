package com.github.curriculeon.engine;

import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;

import java.io.File;


public class CSVToExcelConverter {
    private File source;
    private File destination;

    public CSVToExcelConverter(File csvSource, File csvDestination) {
        this.source = csvSource;
        this.destination = csvDestination;
    }

    public ExcelSpreadSheetWorkBookFile parseToExcel(File excelSpreadSheetFileToParse) {
        CSVSanitizer csvSanitizer = new CSVSanitizer(source, destination);
        ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBookFile(excelSpreadSheetFileToParse);
        GradeParser gradeParser = new GradeParser(excelSpreadSheetWorkBook, csvSanitizer);
        gradeParser.parseToExcel();
        return gradeParser.getExcelSpreadSheetWorkBookDestination();
    }
}