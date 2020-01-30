package com.github.curriculeon.excel.tabledata.metadata;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.formula.atp.DateParser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import java.time.LocalDate;
import java.util.Date;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
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

    public static String toString(Cell cell) {
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

    public CellType getCellTypeEnum(Cell cell) {
        return cellTypeParser.apply(cell);
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
}