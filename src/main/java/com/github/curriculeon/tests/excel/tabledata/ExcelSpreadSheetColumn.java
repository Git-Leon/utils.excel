package com.github.curriculeon.tests.excel.tabledata;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.tabledata.metadata.CellTypeAdapter;
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
    @Override
    public Cell getCell(int rowNumber) {
        return find(cell -> cell.getRowIndex() == rowNumber).orElse(sheet
                .getRow(getDimensionIndex())
                .createCell(rowNumber));
    }


    /**
     * @param rowHeader - name of the row-header that this cell belongs to
     * @return cell to be found at respective row-header
     */
    public Cell getCellByRowHeader(String rowHeader) {
        return new ExcelSpreadSheet(this.sheet)
                .getRowHeaders()
                .getData()
                .stream()
                .filter(cell -> CellTypeAdapter.getCellValue(cell).equals(rowHeader))
                .findFirst()
                .get();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell cell : this) {
            sb.append(String.format("\n[ %s ]", CellTypeAdapter.getCellValue(cell)));
        }
        return sb.toString();
    }
}