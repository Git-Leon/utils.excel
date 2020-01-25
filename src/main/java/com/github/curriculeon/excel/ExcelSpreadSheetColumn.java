package com.github.curriculeon.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:03 AM
 */
public class ExcelSpreadSheetColumn extends ExcelSpreadSheetTableDataArray {
    public ExcelSpreadSheetColumn(Sheet sheet, Integer columnNumber, List<Cell> data) {
        super(sheet, columnNumber, data);
    }

    public Cell getCell(int columnNumber) {
        return find(cell -> cell.getColumnIndex() == columnNumber).orElse(sheet
                .getRow(rowNumber)
                .createCell(columnNumber));
    }
}