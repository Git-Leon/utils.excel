package com.github.curriculeon;

import com.github.curriculeon.engine.CSVToExcelConverter;
import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.excel.tabledata.metadata.CellTypeAdapter;
import com.github.curriculeon.utils.ResourceUtils;
import com.github.curriculeon.utils.string.StringEvaluator;
import com.github.git_leon.collectionutils.maps.DescriptiveMap;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyObject implements Runnable {
    public void run() {
        File source = ResourceUtils.getResourceFile("grades.csv");
        File destination = ResourceUtils.getDuplicateFile(source.getName());
        File excelFileToClone = ResourceUtils.getResourceFile("java-developer-philly-rubric-template.xlsx");

        CSVToExcelConverter csvToExcelConverter = new CSVToExcelConverter(source, destination);
        ExcelSpreadSheetWorkBookFile destinationWorkbook = csvToExcelConverter.parseToExcel(excelFileToClone);
        ExcelSpreadSheetWorkBookFile sourceWorkbook = new ExcelSpreadSheetWorkBookFile(excelFileToClone);

        ExcelSpreadSheet gradesCSV = destinationWorkbook.getExcelSpreadSheetByIndex(0).get();
        List<String> csvHeaders = gradesCSV.getColumnHeaders().getStringData();
        Map<String, ExcelSpreadSheet> csvHeaderToExcelSpreadSheetMap = new HashMap<>();
        for(String csvHeader: csvHeaders) {
            Sheet sheet = destinationWorkbook.getMostSimilarSheet(csvHeader);
            csvHeaderToExcelSpreadSheetMap.put(csvHeader, new ExcelSpreadSheet(sheet));
        }

        System.out.println(new DescriptiveMap<>(csvHeaderToExcelSpreadSheetMap));
    }
}
