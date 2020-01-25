package com.github.curriculeon;

import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:05 AM
 */
public interface ExcelSpreadSheetTableData {
    List<Cell> getData();

    default public Cell get(Integer index) {
        return getData().get(index);
    }

    default void setValue(double var1) {
        for (Cell datum : getData()) {
            datum.setCellValue(var1);
        }
    }

    default void setValue(Date var1) {
        for (Cell datum : getData()) {
            datum.setCellValue(var1);
        }
    }

    default void setValue(Calendar var1) {
        for (Cell datum : getData()) {
            datum.setCellValue(var1);
        }
    }

    default void setValue(RichTextString var1) {
        for (Cell datum : getData()) {
            datum.setCellValue(var1);
        }
    }
    default void setCellFormula(String toString) {
        for(Cell datum : getData()) {
            datum.setCellFormula(toString);
        }
    }

}
