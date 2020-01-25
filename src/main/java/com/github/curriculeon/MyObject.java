package com.github.curriculeon;

import com.github.curriculeon.csv.CSVParser;
import com.github.curriculeon.csv.GradeParser;
import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetFile;
import com.github.curriculeon.utils.ResourceUtils;

import java.io.File;

public class MyObject implements Runnable {
    public void run() {
        File source = ResourceUtils.getResourceFile("grades.csv");
        File destination = ResourceUtils.loadDuplicate(source.getName());
        CSVParser csvParser = new CSVParser(source, destination);
        File spreadSheetFile = ResourceUtils.getResourceFile("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetFile excelSpreadSheetFile = new ExcelSpreadSheetFile(spreadSheetFile);
        GradeParser gradeParser = new GradeParser(excelSpreadSheetFile, csvParser);
        gradeParser.parseToExcel();
    }
}
