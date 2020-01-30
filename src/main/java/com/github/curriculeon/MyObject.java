package com.github.curriculeon;

import com.github.curriculeon.engine.CSVToExcelConverter;
import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBook;
import com.github.curriculeon.utils.ResourceUtils;

import java.io.File;
import java.util.Optional;

public class MyObject implements Runnable {
    public void run() {
        File source = ResourceUtils.getResourceFile("grades.csv");
        File destination = ResourceUtils.getDuplicateFile(source.getName());
        File excelFileToClone = ResourceUtils.getResourceFile("java-developer-philly-rubric-template.xlsx");

        CSVToExcelConverter csvToExcelConverter = new CSVToExcelConverter(source, destination);
        ExcelSpreadSheetWorkBook workbook = csvToExcelConverter.parseToExcel(excelFileToClone);
        ExcelSpreadSheetWorkBook sourceWorkBook = new ExcelSpreadSheetWorkBook(excelFileToClone);

        Optional<ExcelSpreadSheet> sheet = workbook.getExcelSpreadSheetByIndex(0);
        workbook.deleteSheetsAfter(sourceWorkBook.size());
        workbook.flush();
    }
}
