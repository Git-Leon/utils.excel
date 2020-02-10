package com.github.curriculeon.tests.excel.tabledata.cell;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import java.util.Date;

/**
 * @author leonhunter
 * @created 02/09/2020 - 11:13 PM
 */
public class ExcelSpreadSheetCellDecorator implements ExcelSpreadSheetCellDecoratorInterface {
    private Cell cell;

    public ExcelSpreadSheetCellDecorator(Cell cell) {
        this.cell = cell;
    }


    public CellType getCellTypeEnum() {
        return CellType.valueOf(cell.getCellTypeEnum().name());
    }

    public String getCellValue() {
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

    public void setValue(String valueToBeSet) {
        try {
            // String values
            cell.setCellValue(valueToBeSet);
        } catch (IllegalStateException failedToParseAsString) {
            try { // Numeric Values
                cell.setCellValue(Double.parseDouble(valueToBeSet));
            } catch (IllegalStateException | NumberFormatException failedToParseAsDouble) {
                try { // Boolean Values
                    cell.setCellValue(Boolean.parseBoolean(valueToBeSet));
                } catch (Throwable failedToParseAsBoolean) {
                    try { // Date Values
                        double doubleValue = Double.parseDouble(valueToBeSet);
                        Date dateValue;
                        try {
                            dateValue = DateUtil.getJavaDate(doubleValue, true);
                        } catch (Throwable failedToParseUsing1904Windowing) {
                            dateValue = DateUtil.getJavaDate(doubleValue, false);
                        }
                        cell.setCellValue(dateValue);
                    } catch (Throwable failedToParse) {
                        throw new Error(failedToParse);
                    }
                }
            }
        }
    }

    @Override
    public Cell getCell() {
        return cell;
    }
}
