package com.github.curriculeon.tests.excel.tabledata;

import com.github.curriculeon.tests.excel.tabledata.metadata.CellTypeAdapter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract public class ExcelSpreadSheetTableDataArray implements ExcelSpreadSheetTableData {
    protected final Sheet sheet;
    private final Integer dimensionIndex;
    private final List<Cell> data;

    public ExcelSpreadSheetTableDataArray(Sheet sheet, Integer dimensionIndex, List<Cell> data) {
        this.sheet = sheet;
        this.dimensionIndex = dimensionIndex;
        this.data = data;
    }


    public Integer getDimensionIndex() {
        return dimensionIndex;
    }

    public List<Cell> getData() {
        return data;
    }


    @Override
    public Iterator<Cell> iterator() {
        return getData().iterator();
    }

    @Override
    public String toString() {
        return getClass() + "{" +
                "\ndimensionIndex=" + dimensionIndex +
                "\nsheetType=" + sheet.getClass() +
                "\nsheet=" + sheet.toString() +
                "\ndata=" + data +
                "\n}";
    }
}
