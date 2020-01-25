package com.github.curriculeon.excel;

import org.apache.poi.ss.usermodel.Cell;

import java.util.List;

abstract public class ExcelSpreadSheetTableDataArray implements ExcelSpreadSheetTableData {
    private List<Cell> data;

    public ExcelSpreadSheetTableDataArray(List<Cell> data) {
        this.data = data;
    }

    public List<Cell> getData() {
        return data;
    }
}
