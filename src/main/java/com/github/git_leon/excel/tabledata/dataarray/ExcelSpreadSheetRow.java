package com.github.git_leon.excel.tabledata.dataarray;

import com.github.git_leon.excel.tabledata.cell.ExcelSpreadSheetCell;
import com.github.git_leon.excel.ExcelSpreadSheet;
import com.github.git_leon.excel.tabledata.cell.metadata.CellTypeAdapter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:00 AM
 */
public class ExcelSpreadSheetRow extends AbstractExcelSpreadSheetTableDataArray {
    public ExcelSpreadSheetRow(Sheet sheet, Integer rowNumber) {
        super(sheet, rowNumber);
    }

    @Override
    public List<Cell> getData() {
        final Iterator<Cell> iterator = getRow().cellIterator();
        final List<Cell> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);
        return list;
    }

    public Row getRow() {
        Row row = sheet.getRow(getDimensionIndex());
        if (row == null) {
            row = sheet.createRow(getDimensionIndex());
        }
        return row;
    }

    /**
     * @param columnNumber - the column of this row that you would like to fetch data from
     * @return the cell at this position, otherwise create a new cell at this position
     */
    @Override
    public ExcelSpreadSheetCell getCell(int columnNumber) {
        return new ExcelSpreadSheetCell(getData().get(columnNumber));
    }

    @Override
    public void setValue(String value, Integer column) {
        this.getCell(column).setValue(value);
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
        return getRow().getPhysicalNumberOfCells();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell cell : this) {
            sb.append("[ " + CellTypeAdapter.getCellValue(cell) + " ]");
        }
        return sb
                .toString()
                .replaceAll("\n", "")
                .concat("\n");
    }
}
