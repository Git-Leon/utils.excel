package com.github.curriculeon.excel.tabledata;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:00 AM
 */
public class ExcelSpreadSheetRow extends ExcelSpreadSheetTableDataArray {
    public ExcelSpreadSheetRow(Sheet sheet, Integer rowNumber, List<Cell> data) {
        super(sheet, rowNumber, data);
    }

    public Cell getCell(int columnNumber) {
        return find(cell -> cell.getRowIndex() == columnNumber).orElse(sheet
                .getRow(dimensionIndex)
                .createCell(columnNumber));
    }
}
