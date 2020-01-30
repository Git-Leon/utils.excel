package com.github.curriculeon.engine;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBook;
import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.utils.ResourceUtils;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leonhunter
 * @created 01/24/2020 - 9:58 PM
 */
public class GradeParser {
    private final ExcelSpreadSheetWorkBook excelSpreadSheetWorkBookSource;
    private final ExcelSpreadSheetWorkBook excelSpreadSheetWorkBookDestination;
    private CSVSanitizer csvSanitizer;

    public GradeParser(ExcelSpreadSheetWorkBook excelSource, CSVSanitizer csvSanitizer) {
        this.csvSanitizer = csvSanitizer;
        this.excelSpreadSheetWorkBookSource = excelSource;
        this.excelSpreadSheetWorkBookDestination = excelSource.copyTo(new File(new StringBuilder()
                .append(ResourceUtils.getResourceDirectoryPath())
                .append("/")
                .append("java-developer-philly-rubric-template_")
                .append(System.nanoTime())
                .append(".xlsx")
                .toString()));
    }

    public void parseToExcel() {
        String newSheetName = "Grades Parsed From Canvas";
        excelSpreadSheetWorkBookDestination.addSheets(excelSpreadSheetWorkBookSource.getSheetsFromWorkBook());
        ExcelSpreadSheet newSheet = excelSpreadSheetWorkBookDestination.getExcelSpreadSheetByName(newSheetName).get();

        List<List<String>> rows = csvSanitizer.parseRows();
        List<List<Cell>> cellList = new ArrayList<>();
        for (int rowNumber = 0; rowNumber < rows.size(); rowNumber++) {
            List<String> stringListData = rows.get(rowNumber);
            List<Cell> cellListData = new ArrayList<>();
            for (int columnNumber = 0; columnNumber < stringListData.size(); columnNumber++) {
                String cellValue = stringListData.get(columnNumber);
                Cell cell = newSheet.getCell(rowNumber, columnNumber);
                cell.setCellValue(cellValue);
                cellListData.add(cell);
            }
            ExcelSpreadSheetRow row = new ExcelSpreadSheetRow(newSheet.getSheet(), rowNumber, cellListData);
            newSheet.addRow(row, row.getDimensionIndex());
            excelSpreadSheetWorkBookDestination.addSheet(newSheet.getSheet(), newSheetName);
        }

    }

}
