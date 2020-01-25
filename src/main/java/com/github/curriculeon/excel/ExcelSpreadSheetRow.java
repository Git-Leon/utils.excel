package com.github.curriculeon.excel;

import org.apache.poi.ss.usermodel.Cell;

import java.util.List;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:00 AM
 */
public class ExcelSpreadSheetRow extends ExcelSpreadSheetTableDataArray {
    public ExcelSpreadSheetRow(List<Cell> data) {
        super(data);
    }

    public Cell getCell(int columnNumber) {
        return find(cell -> cell.getColumnIndex() == columnNumber).get(0);
    }
}
