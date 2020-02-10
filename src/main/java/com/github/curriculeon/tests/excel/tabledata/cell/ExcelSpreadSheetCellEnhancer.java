package com.github.curriculeon.tests.excel.tabledata.cell;

import org.apache.poi.ss.usermodel.DateUtil;

import java.util.Date;

/**
 * @author leonhunter
 * @created 02/09/2020 - 11:13 PM
 */
public interface ExcelSpreadSheetCellEnhancer extends ExcelSpreadSheetCellDecoratorInterface {

    default String getCellValue() {
        try {
            // String values
            return getCell().getStringCellValue();
        } catch (IllegalStateException failedToParseAsString) {
            try { // Numeric Values
                return String.valueOf(getCell().getNumericCellValue());
            } catch (IllegalStateException | NumberFormatException failedToParseAsDouble) {
                try { // Boolean Values
                    return String.valueOf(getCell().getBooleanCellValue());
                } catch (Throwable failedToParseAsBoolean) {
                    try { // Date Values
                        return String.valueOf(getCell().getDateCellValue());
                    } catch (Throwable failedToParse) {
                        throw new Error(failedToParse);
                    }
                }
            }
        }
    }

    default void setValue(String valueToBeSet) {
        try {
            // String values
            getCell().setCellValue(valueToBeSet);
        } catch (IllegalStateException failedToParseAsString) {
            try { // Numeric Values
                getCell().setCellValue(Double.parseDouble(valueToBeSet));
            } catch (IllegalStateException | NumberFormatException failedToParseAsDouble) {
                try { // Boolean Values
                    getCell().setCellValue(Boolean.parseBoolean(valueToBeSet));
                } catch (Throwable failedToParseAsBoolean) {
                    try { // Date Values
                        double doubleValue = Double.parseDouble(valueToBeSet);
                        Date dateValue;
                        try {
                            dateValue = DateUtil.getJavaDate(doubleValue, true);
                        } catch (Throwable failedToParseUsing1904Windowing) {
                            dateValue = DateUtil.getJavaDate(doubleValue, false);
                        }
                        getCell().setCellValue(dateValue);
                    } catch (Throwable failedToParse) {
                        throw new Error(failedToParse);
                    }
                }
            }
        }
    }
}
