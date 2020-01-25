package com.github.curriculeon.excel;

import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:05 AM
 */
public interface ExcelSpreadSheetTableData {
    List<Cell> getData();

    default List<Cell> find(Predicate<Cell> filterClause) {
        return new ArrayList<>(getData()
                .stream()
                .filter(filterClause)
                .collect(Collectors.toList()));
    }

    default void setValue(double var1) {
        getData().forEach(cell -> cell.setCellValue(var1));
    }

    default void setValue(Date var1) {
        getData().forEach(cell -> cell.setCellValue(var1));
    }

    default void setValue(Calendar var1) {
        getData().forEach(cell -> cell.setCellValue(var1));
    }

    default void setValue(RichTextString var1) {
        getData().forEach(cell -> cell.setCellValue(var1));
    }
    default void setCellFormula(String toString) {
        getData().forEach(cell -> cell.setCellFormula(toString));
    }

}
