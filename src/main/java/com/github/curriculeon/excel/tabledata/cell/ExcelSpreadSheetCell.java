package com.github.curriculeon.excel.tabledata.cell;

import org.apache.poi.ss.usermodel.Cell;

/**
 * Created by leon on 2/10/2020.
 */
public class ExcelSpreadSheetCell implements ExcelSpreadSheetCellEnhancer {
    private Cell cell;

    public ExcelSpreadSheetCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public Cell getCell() {
        return cell;
    }
}
