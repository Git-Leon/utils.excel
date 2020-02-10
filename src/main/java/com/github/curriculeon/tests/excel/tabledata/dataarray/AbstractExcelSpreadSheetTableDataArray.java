package com.github.curriculeon.tests.excel.tabledata.dataarray;

import org.apache.poi.ss.usermodel.Sheet;

abstract public class AbstractExcelSpreadSheetTableDataArray implements ExcelSpreadSheetTableDataArrayInterface {
    protected final Sheet sheet;
    private final Integer dimensionIndex;

    public AbstractExcelSpreadSheetTableDataArray(Sheet sheet, Integer dimensionIndex) {
        this.sheet = sheet;
        this.dimensionIndex = dimensionIndex;
    }

    public Integer getDimensionIndex() {
        return dimensionIndex;
    }


    @Override
    public String toString() {
        return getClass() + "{" +
                "\ndimensionIndex=" + dimensionIndex +
                "\nsheetType=" + sheet.getClass().getSimpleName() +
                "\ndata=" + getData() +
                "\nsheet=" + sheet.toString() +
                "\n}";
    }
}
