package com.github.curriculeon.excel.tabledata;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.tabledata.metadata.CellTypeAdapter;
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

    /**
     * @param columnNumber - the column of this row that you would like to fetch data from
     * @return the cell at this position, otherwise create a new cell at this position
     */
    public Cell getCell(int columnNumber) {
        return find(cell -> cell.getRowIndex() == columnNumber).orElse(sheet
                .getRow(getDimensionIndex())
                .createCell(columnNumber));
    }

    public Cell getCellByColumnHeader(String columnHeader) {
        return new ExcelSpreadSheet(this.sheet)
                .getColumnHeaders()
                .getData()
                .stream()
                .filter(cell -> CellTypeAdapter.getCellValue(cell).equals(columnHeader))
                .findFirst()
                .get();
    }
}
