package com.github.curriculeon.engine;

import com.github.curriculeon.excel.ExcelSpreadSheetWorkBook;
import com.github.curriculeon.utils.ResourceUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class CSVToExcelConverter {
    private File source;
    private File destination;

    public CSVToExcelConverter(File csvSource, File csvDestination) {
        this.source = csvSource;
        this.destination = csvDestination;
    }

    public ExcelSpreadSheetWorkBook parseToExcel(File excelSpreadSheetFileToParse) {
        CSVSanitizer csvSanitizer = new CSVSanitizer(source, destination);
        ExcelSpreadSheetWorkBook excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBook(excelSpreadSheetFileToParse);
        GradeParser gradeParser = new GradeParser(excelSpreadSheetWorkBook, csvSanitizer);
        gradeParser.parseToExcel();
        return gradeParser.getExcelSpreadSheetWorkBookDestination();
    }
}