package com.github.curriculeon.excel.tabledata.cell.metadata;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * Created by leon on 1/30/2020.
 */
public enum CellTypeAdapter {
    STRING,
    NUMERIC,
    BOOLEAN,
    FORMULA,
    BLANK;

    private final Function<Cell, CellType> cellTypeParser;

    CellTypeAdapter() {
        this.cellTypeParser = (cell) -> CellType.valueOf(cell.getCellTypeEnum().name());
    }

    public CellType getCellTypeEnum(Cell cell) {
        return cellTypeParser.apply(cell);
    }

    public static String getCellValue(Cell cell) {
        try {
            // String values
            return cell.getStringCellValue();
        } catch (IllegalStateException failedToParseAsString) {
            try { // Numeric Values
                return String.valueOf(cell.getNumericCellValue());
            } catch (IllegalStateException | NumberFormatException failedToParseAsDouble) {
                try { // Boolean Values
                    return String.valueOf(cell.getBooleanCellValue());
                } catch (Throwable failedToParseAsBoolean) {
                    try { // Date Values
                        return String.valueOf(cell.getDateCellValue());
                    } catch (Throwable failedToParse) {
                        throw new Error(failedToParse);
                    }
                }
            }
        }
    }

    public static void setValue(Cell cellToBeMutated, String valueToBeSet) {
        try {
            // String values
            cellToBeMutated.setCellValue(valueToBeSet);
        } catch (IllegalStateException failedToParseAsString) {
            try { // Numeric Values
                cellToBeMutated.setCellValue(Double.parseDouble(valueToBeSet));
            } catch (IllegalStateException | NumberFormatException failedToParseAsDouble) {
                try { // Boolean Values
                    cellToBeMutated.setCellValue(Boolean.parseBoolean(valueToBeSet));
                } catch (Throwable failedToParseAsBoolean) {
                    try { // Date Values
                        double doubleValue = Double.parseDouble(valueToBeSet);
                        Date dateValue;
                        try {
                            dateValue = DateUtil.getJavaDate(doubleValue, true);
                        } catch (Throwable failedToParseUsing1904Windowing) {
                            dateValue = DateUtil.getJavaDate(doubleValue, false);
                        }
                        cellToBeMutated.setCellValue(dateValue);
                    } catch (Throwable failedToParse) {
                        throw new Error(failedToParse);
                    }
                }
            }
        }
    }

    public static List<List<Cell>> toListOfCellLists(Sheet sheet, List<List<String>> cellDataArrays) {
        List<List<Cell>> tableDataArrays = new ArrayList<>();
        for(List<String> tableDataArray : cellDataArrays) {
            tableDataArrays.add(toCellList(sheet, tableDataArray));
        }
        return tableDataArrays;
    }

    public static List<Cell> toCellList(Sheet sheet, List<String> cellData) {
        List<Cell> result = new ArrayList<>();
        ExcelSpreadSheet spreadSheet = new ExcelSpreadSheet(sheet);
        int rowIndex = 0;
        int numberOfColumns = spreadSheet.getColumns().size();
        Row row = spreadSheet.getRow(rowIndex).getRow();
        for(int i=0 ;i<cellData.size(); i++) {
            String data = cellData.get(i);
            int columnIndex = numberOfColumns+i;
            Cell cell = row.createCell(columnIndex);
            CellTypeAdapter.setValue(cell, data);
            result.add(cell);
        }
        return result;
    }
}
