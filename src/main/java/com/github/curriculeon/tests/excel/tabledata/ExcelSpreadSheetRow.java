package com.github.curriculeon.tests.excel.tabledata;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.tabledata.metadata.CellTypeAdapter;
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

    public Integer getNumberOfColumns() {
        return sheet.getRow(getDimensionIndex()).getPhysicalNumberOfCells();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell cell : this) {
            sb.append(String.format("[ %s ]", CellTypeAdapter.getCellValue(cell)));
        }
        return sb
                .toString()
                .replaceAll("\n", "")
                .concat("\n");
    }
}
