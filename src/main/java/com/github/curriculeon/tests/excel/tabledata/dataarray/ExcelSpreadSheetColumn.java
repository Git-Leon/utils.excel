package com.github.curriculeon.tests.excel.tabledata.dataarray;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.tabledata.cell.metadata.CellTypeAdapter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:03 AM
 */
public class ExcelSpreadSheetColumn extends AbstractExcelSpreadSheetTableDataArray {

    public ExcelSpreadSheetColumn(Sheet sheet, Integer columnNumber) {
        super(sheet, columnNumber);
    }

    @Override
    public List<Cell> getData() {
        List<Cell> list = new ArrayList<>();
        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row != null) {
                Cell cell = row.getCell(getDimensionIndex());
                if (cell != null) {
                    list.add(cell);
                }
            }
        }
        return list;
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