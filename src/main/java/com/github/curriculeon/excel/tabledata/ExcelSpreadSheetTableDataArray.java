package com.github.curriculeon.excel.tabledata;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

abstract public class ExcelSpreadSheetTableDataArray implements ExcelSpreadSheetTableData {
    protected final Sheet sheet;
    protected final Integer rowNumber;
    private final List<Cell> data;

    public ExcelSpreadSheetTableDataArray(Sheet sheet, Integer rowNumber, List<Cell> data) {
        this.sheet = sheet;
        this.rowNumber = rowNumber;
        this.data = data;
    }

    public List<Cell> getData() {
        return data;
    }
}
