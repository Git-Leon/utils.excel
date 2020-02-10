package com.github.curriculeon.tests.excel.tabledata.dataarray;

import com.github.curriculeon.tests.excel.tabledata.cell.metadata.CellTypeAdapter;
import org.apache.poi.ss.usermodel.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:05 AM
 */
public interface ExcelSpreadSheetTableDataArrayInterface extends Iterable<Cell> {
    List<Cell> getData();

    Cell getCell(int columnNumber);

    default Optional<Cell> find(Predicate<Cell> filterClause) {
        return getData()
                .stream()
                .filter(filterClause)
                .findFirst();
    }

    default List<String> getStringData() {
        return getData()
                .stream()
                .map(CellTypeAdapter::getCellValue)
                .collect(Collectors.toList());
    }

    default String getCellData(Integer index) {
        return CellTypeAdapter.getCellValue(getData().get(index));
    }

    default String getHeader() {
        return getCellData(0);
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
