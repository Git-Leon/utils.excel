package com.github.curriculeon;

import com.github.curriculeon.engine.CSVToExcelConverter;
import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetColumn;
import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.utils.ResourceUtils;
import com.github.curriculeon.utils.StringEvaluator;
import com.github.git_leon.collectionutils.maps.DescriptiveMap;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
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

        ExcelSpreadSheet gradesCSV = destinationWorkbook.getExcelSpreadSheetByIndex(0).get();
        ExcelSpreadSheetRow csvHeaders = gradesCSV.getColumnHeaders();
        List<String> csvHeadersStrings = csvHeaders.getStringData();
        Map<String, ExcelSpreadSheet> csvHeaderToExcelSpreadSheetMap = new HashMap<>();
        for(String sheetName : destinationWorkbook.getSheetNamesFromWorkBook()) {
            StringEvaluator evaluator = new StringEvaluator(sheetName);
            String mostSimilarCsvHeader = evaluator.getMostSimilar(csvHeadersStrings);
            ExcelSpreadSheetColumn mostLikelyColumn = gradesCSV.getColumn(mostSimilarCsvHeader);
            Sheet mostLikelySheet = destinationWorkbook.getMostSimilarSheet(mostSimilarCsvHeader);
            ExcelSpreadSheet mostLikelyExcelSpreadSheet = new ExcelSpreadSheet(mostLikelySheet);
        }

        System.out.println(new DescriptiveMap<>(csvHeaderToExcelSpreadSheetMap));
    }
}
