package com.github.curriculeon.tests.excel;

import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetColumn;
import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetTableDataArray;
import com.github.curriculeon.tests.excel.tabledata.metadata.CellTypeAdapter;
import com.github.curriculeon.utils.StringEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author leonhunter
 * @created 01/31/2020 - 5:28 PM
 */
public interface ExcelSpreadSheetWorkBookInterface extends Iterable<ExcelSpreadSheet> {

    Workbook getWorkBook();

    default int size() {
        return getWorkBook().getNumberOfSheets();
    }

    default ExcelSpreadSheet createNewExcelSpreadSheet(String name) {
        return new ExcelSpreadSheet(getWorkBook().createSheet(name));
    }

    default void setSheetOrder(String sheetName, Integer newSheetIndex) {
        getWorkBook().setSheetOrder(sheetName, newSheetIndex);
    }

    default void setActive(Sheet newSheet) {
        getWorkBook().setActiveSheet(getWorkBook().getSheetIndex(newSheet.getSheetName()));
    }

    default Iterator<ExcelSpreadSheet> iterator() {
        return this.getExcelSpreadSheets().iterator();
    }

    default List<Sheet> getSheetsFromWorkBook() {
        List<Sheet> list = new ArrayList<>();
        int numberOfSheets = getWorkBook().getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = getWorkBook().getSheetAt(i);
            list.add(sheet);
        }
        return list;
    }

    default List<String> getSheetNamesFromWorkBook() {
        return getSheetsFromWorkBook()
                .stream()
                .map(Sheet::getSheetName)
                .collect(Collectors.toList());
    }

    default Sheet getMostSimilarSheet(String name) {
        StringEvaluator stringEvaluator = new StringEvaluator(name);
        String mostSimilarSheetName = stringEvaluator.getMostSimilar(getSheetNamesFromWorkBook());
        return getSheetsFromWorkBook()
                .stream()
                .filter(sheet -> sheet.getSheetName().equals(mostSimilarSheetName))
                .findFirst()
                .get();
    }

    default Boolean containsSheet(Sheet sheet) {
        return getSheetsFromWorkBook().contains(sheet);
    }

    default Boolean containsSheet(String name) {
        return getSheetsFromWorkBook()
                .stream()
                .anyMatch(excelSheet -> excelSheet.getSheetName().equals(name));
    }

    default List<ExcelSpreadSheet> getExcelSpreadSheets() {
        return getSheetsFromWorkBook()
                .stream()
                .map(ExcelSpreadSheet::new)
                .collect(Collectors.toList());
    }

    default ExcelSpreadSheet createExcelSpreadSheetByName(String sheetName) {
        return getExcelSpreadSheetByName(sheetName)
                .or(() -> Optional.of(getWorkBook().createSheet(sheetName)))
                .map(ExcelSpreadSheet::new)
                .get();

    }

    default Optional<Sheet> getExcelSpreadSheetByName(String sheetName) {
        return getSheetsFromWorkBook()
                .stream()
                .filter(sheet -> sheet.getSheetName().equals(sheetName))
                .findFirst();
    }

    default Optional<ExcelSpreadSheet> getExcelSpreadSheetByIndex(Integer index) {
        try {
            return getSheetsFromWorkBook()
                    .stream()
                    .filter(sheet -> sheet.equals(getWorkBook().getSheetAt(index)))
                    .map(ExcelSpreadSheet::new)
                    .findFirst();
        } catch (IllegalArgumentException iae) {
            return Optional.empty();
        }
    }
}
