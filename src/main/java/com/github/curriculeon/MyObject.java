package com.github.curriculeon;

import com.github.curriculeon.engine.CSVSanitizer;
import com.github.curriculeon.engine.CSVToExcelConverter;
import com.github.curriculeon.engine.GradeParser;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBook;
import com.github.curriculeon.utils.ResourceUtils;

import java.io.File;

public class MyObject implements Runnable {
    public void run() {
        File source = ResourceUtils.getResourceFile("grades.csv");
        File destination = ResourceUtils.getDuplicateFile(source.getName());
        CSVToExcelConverter csvToExcelConverter = new CSVToExcelConverter(source, destination);
        File excelFileToClone = ResourceUtils.getResourceFile("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBook workbook = csvToExcelConverter.parseToExcel(excelFileToClone);
    }
}
