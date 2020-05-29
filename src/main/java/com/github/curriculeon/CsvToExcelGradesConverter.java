package com.github.curriculeon;

import com.github.curriculeon.csv.CsvParser;
import com.github.curriculeon.csv.CsvToExcelSpreadSheetConverter;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.utils.io.DirectoryReference;

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
        CsvToExcelSpreadSheetConverter csvToExcelSpreadSheetConverter = new CsvToExcelSpreadSheetConverter(excelSpreadSheetWorkBook, DirectoryReference.TARGETDIRECTORY
            .getFileFromDirectory(new StringBuilder()
                .append("PARSED-")
                .append("java-developer-philly-rubric-template_")
                .append(System.nanoTime())
                .append(".xlsx")
                .toString()), csvParser);
        csvToExcelSpreadSheetConverter.parseToExcel("Grades Parsed From Canvas");
        return csvToExcelSpreadSheetConverter.getExcelSpreadSheetWorkBookDestination();
    }
}
