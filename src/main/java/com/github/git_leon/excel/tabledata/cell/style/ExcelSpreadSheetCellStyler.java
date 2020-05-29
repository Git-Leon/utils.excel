package com.github.git_leon.excel.tabledata.cell.style;

import com.github.git_leon.excel.ExcelSpreadSheet;
import org.apache.poi.ss.usermodel.*;

import java.util.function.Predicate;

/**
 * @author leonhunter
 * @created 01/25/2020 - 1:59 AM
 */
public class ExcelSpreadSheetCellStyler  {
    private ExcelSpreadSheet excelSpreadSheet;

    public ExcelSpreadSheetCellStyler(ExcelSpreadSheet excelSpreadSheet) {
        this.excelSpreadSheet = excelSpreadSheet;
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

            boolean isAfterStartingRow = cellRow >= startingRow;
            boolean isBeforeEndingRow = cellRow <= endingRow;
            boolean isAfterStartingColumn = cellColumn >= startingColumn;
            boolean isAfterEndingColumn = cellColumn <= endingColumn;

            return isAfterStartingRow &&
                    isBeforeEndingRow &&
                    isAfterStartingColumn &&
                    isAfterEndingColumn;
        });
    }

    public void setStyle(CellStyle var1) {

    }

    public Workbook getWorkBook() {
        return excelSpreadSheet.getWorkBook();
    }

    public void setCellComment(Comment var1) {

    }


    public void setHyperlink(Hyperlink var1) {

    }

}
