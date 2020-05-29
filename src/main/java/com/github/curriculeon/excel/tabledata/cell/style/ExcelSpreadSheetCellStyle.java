package com.github.curriculeon.excel.tabledata.cell.style;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import java.util.function.Predicate;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:20 AM
 * Styles an individual cell on an excel spread sheet
 */
public class ExcelSpreadSheetCellStyle {
    private CellStyle cellStyle;


    public ExcelSpreadSheetCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public void setCondition(Predicate<Cell> conditionToStyleCell) {

    }

    public void setRange(Cell startingCell, Cell endingCell) {
        Integer startingRow = startingCell.getRowIndex();
        Integer startingColumn = startingCell.getColumnIndex();
        Integer endingRow = endingCell.getRowIndex();
        Integer endingColumn = endingCell.getColumnIndex();

        setCondition(cell -> {
            int cellRow = cell.getRowIndex();
            int cellColumn = cell.getColumnIndex();

            Boolean isAfterStartingRow = cellRow >= startingRow;
            Boolean isBeforeEndingRow = cellRow <= endingRow;
            Boolean isAfterStartingColumn = cellColumn >= startingColumn;
            Boolean isAfterEndingColumn = cellColumn <= endingColumn;

            return isAfterStartingRow &&
                    isBeforeEndingRow &&
                    isAfterStartingColumn &&
                    isAfterEndingColumn;
        });
    }

}
