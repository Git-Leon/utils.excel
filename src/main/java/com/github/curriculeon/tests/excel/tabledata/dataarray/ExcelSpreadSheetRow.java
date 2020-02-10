package com.github.curriculeon.tests.excel.tabledata.dataarray;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.tabledata.cell.metadata.CellTypeAdapter;
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
        Row row =  sheet.getRow(getDimensionIndex());
        if(row == null) {
            row = sheet.createRow(getDimensionIndex());
        }
        return row;
    }

    /**
     * @param columnNumber - the column of this row that you would like to fetch data from
     * @return the cell at this position, otherwise create a new cell at this position
     */
    public Cell getCell(int columnNumber) {
        return find(cell -> cell.getColumnIndex() == columnNumber).orElse(sheet
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
