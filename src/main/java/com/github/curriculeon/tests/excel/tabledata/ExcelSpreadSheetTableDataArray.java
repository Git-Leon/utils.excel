package com.github.curriculeon.tests.excel.tabledata;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

abstract public class ExcelSpreadSheetTableDataArray implements ExcelSpreadSheetTableData {
    protected final Sheet sheet;
    private final Integer dimensionIndex;
    private final List<Cell> data;

    public ExcelSpreadSheetTableDataArray(Sheet sheet, Integer dimensionIndex, List<Cell> data) {
        this.sheet = sheet;
        this.dimensionIndex = dimensionIndex;
        this.data = data;
    }

    public Integer getDimensionIndex() {
        return dimensionIndex;
    }

    @Override
    public List<Cell> getData() {
        return data
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return getClass() + "{" +
                "\ndimensionIndex=" + dimensionIndex +
                "\nsheetType=" + sheet.getClass() +
                "\nsheet=" + sheet.toString() +
                "\ndata=" + data +
                "\n}";
    }
}
