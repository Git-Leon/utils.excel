package com.github.curriculeon.excel.tabledata;

import com.github.curriculeon.excel.tabledata.metadata.CellTypeAdapter;
import org.apache.poi.ss.usermodel.*;

import java.util.*;
import java.util.function.Predicate;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:05 AM
 */
public interface ExcelSpreadSheetTableData extends Iterable<Cell> {
    List<Cell> getData();

    Cell getCell(int columnNumber);

    default Optional<Cell> find(Predicate<Cell> filterClause) {
        return getData()
                .stream()
                .filter(filterClause)
                .findFirst();
    }

    default String getHeader() {
        return CellTypeAdapter.getCellValue(getData().get(0));
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

    @Override
    default Iterator<Cell> iterator() {
        return getData().iterator();
    }

}
