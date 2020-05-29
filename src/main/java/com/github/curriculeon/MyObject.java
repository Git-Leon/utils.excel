package com.github.curriculeon;

import com.github.curriculeon.engine.CsvToExcelGradesConverter;
import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.excel.tabledata.dataarray.ExcelSpreadSheetColumn;
import com.github.curriculeon.excel.tabledata.dataarray.ExcelSpreadSheetRow;
import com.github.curriculeon.utils.io.DirectoryReference;
import com.github.curriculeon.utils.StringEvaluator;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyObject implements Runnable {
    public void run() {
        tryLater();
    }

    private void tryLater() {
        File source = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory("grades.csv");
        File destination = DirectoryReference.TARGETDIRECTORY.getDuplicateFile(source.getName());
        File excelFileToClone = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory("java-developer-philly-rubric-template.xlsx");

        CsvToExcelGradesConverter csvToExcelGradesConverter = new CsvToExcelGradesConverter(source, destination);
        ExcelSpreadSheetWorkBookFile destinationWorkbook = csvToExcelGradesConverter.parseToExcel(excelFileToClone);

        ExcelSpreadSheet gradesCSV = destinationWorkbook.getExcelSpreadSheetByIndex(0).get();
        ExcelSpreadSheetRow csvHeaders = gradesCSV.getColumnHeaders();
        List<String> csvHeadersStrings = csvHeaders.getStringData();
        Map<String, ExcelSpreadSheet> csvHeaderToExcelSpreadSheetMap = new HashMap<>();
        List<String> sheetNames = destinationWorkbook.getSheetNamesFromWorkBook();
        for (String sheetName : sheetNames) {
            StringEvaluator evaluator = new StringEvaluator(sheetName);
            String mostSimilarCsvHeader = evaluator.getMostSimilar(csvHeadersStrings);
            ExcelSpreadSheetColumn mostLikelyColumn = gradesCSV.getColumn(mostSimilarCsvHeader);
            Sheet mostLikelySheet = destinationWorkbook.getMostSimilarSheet(mostSimilarCsvHeader);
            ExcelSpreadSheet mostLikelyExcelSpreadSheet = new ExcelSpreadSheet(mostLikelySheet);
            csvHeaderToExcelSpreadSheetMap.put(mostSimilarCsvHeader, mostLikelyExcelSpreadSheet);
            int lastColumnIndex = mostLikelyExcelSpreadSheet.getNumberOfColumns();
            mostLikelyExcelSpreadSheet.addColumn(mostLikelyColumn, lastColumnIndex);
        }
        destinationWorkbook.flush();
        System.out.println(csvHeaderToExcelSpreadSheetMap
        .toString()
        .replaceAll("\n\n\n", ""));
    }
}
