package com.github.curriculeon;

import com.github.curriculeon.engine.CSVToExcelConverter;
import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.excel.tabledata.metadata.CellTypeAdapter;
import com.github.curriculeon.utils.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyObject implements Runnable {
    public void run() {
        File source = ResourceUtils.getResourceFile("grades.csv");
        File destination = ResourceUtils.getDuplicateFile(source.getName());
        File excelFileToClone = ResourceUtils.getResourceFile("java-developer-philly-rubric-template.xlsx");

        CSVToExcelConverter csvToExcelConverter = new CSVToExcelConverter(source, destination);
        ExcelSpreadSheetWorkBookFile destinationWorkbook = csvToExcelConverter.parseToExcel(excelFileToClone);
        ExcelSpreadSheetWorkBookFile sourceWorkbook = new ExcelSpreadSheetWorkBookFile(excelFileToClone);

        ExcelSpreadSheet gradesCSV = destinationWorkbook.getExcelSpreadSheetByIndex(0).get();
        List<String> headers = gradesCSV.getColumnHeaders().getStringData();
        String ele = list.get(10);
        System.out.println(ele);
        System.out.println(destinationWorkbook.getMostSimilarSheet(ele).getSheetName());
    }
}
