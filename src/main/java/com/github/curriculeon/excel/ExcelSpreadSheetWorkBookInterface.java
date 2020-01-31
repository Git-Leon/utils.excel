package com.github.curriculeon.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ExcelSpreadSheetWorkBookInterface extends Iterable<ExcelSpreadSheet> {

    default ExcelSpreadSheet createNewExcelSpreadSheet(String name) {
        return new ExcelSpreadSheet(getWorkBook().createSheet(name));
    }

    Workbook getWorkBook();

    default List<ExcelSpreadSheet> getExcelSpreadSheets() {
        return getSheetsFromWorkBook()
                .stream()
                .map(ExcelSpreadSheet::new)
                .collect(Collectors.toList());
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


    default int size() {
        return getWorkBook().getNumberOfSheets();
    }


    default ExcelSpreadSheet getExcelSpreadSheetByName(String sheetName) {
        return getSheetsFromWorkBook()
                .stream()
                .filter(sheet -> sheet.getSheetName().equals(sheetName))
                .findFirst()
                .or(() -> Optional.of(getWorkBook().createSheet(sheetName)))
                .map(ExcelSpreadSheet::new)
                .get();

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

    default Iterator<ExcelSpreadSheet> iterator() {
        return this.getExcelSpreadSheets().iterator();
    }

    default void setSheetOrder(String sheetName, Integer newSheetIndex) {
        getWorkBook().setSheetOrder(sheetName, newSheetIndex);
    }

    default void setActive(Sheet newSheet) {
        getWorkBook().setActiveSheet(getWorkBook().getSheetIndex(newSheet.getSheetName()));
    }

    default Boolean containsSheet(Sheet sheet) {
        return getSheetsFromWorkBook().contains(sheet);
    }

    default Boolean containsSheet(String name) {
        return getSheetsFromWorkBook()
                .stream()
                .anyMatch(excelSheet -> excelSheet.getSheetName().equals(name));
    }
}
