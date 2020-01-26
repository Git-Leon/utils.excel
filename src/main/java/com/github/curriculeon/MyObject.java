package com.github.curriculeon;

import com.github.curriculeon.engine.CSVSanitizer;
import com.github.curriculeon.engine.GradeParser;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBook;
import com.github.curriculeon.utils.ResourceUtils;

import java.io.File;

public class MyObject implements Runnable {
    public void run() {
        File source = ResourceUtils.getResourceFile("grades.csv");
        File destination = ResourceUtils.getDuplicateFile(source.getName());
        CSVSanitizer csvSanitizer = new CSVSanitizer(source, destination);
        File spreadSheetFile = ResourceUtils.getResourceFile("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBook excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBook(spreadSheetFile);
        GradeParser gradeParser = new GradeParser(excelSpreadSheetWorkBook, csvSanitizer);
        gradeParser.parseToExcel();
    }
}
