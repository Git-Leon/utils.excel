package com.github.curriculeon.excel.tabledata;

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

    /**
     * @param rowNumber - the row of this column that you would like to fetch data from
     * @return the cell at this position, otherwise create a new cell at this position
     */
    public Cell getCell(int rowNumber) {
        return find(cell -> cell.getColumnIndex() == rowNumber).orElse(sheet
                .getRow(getDimensionIndex())
                .createCell(rowNumber));
    }
}