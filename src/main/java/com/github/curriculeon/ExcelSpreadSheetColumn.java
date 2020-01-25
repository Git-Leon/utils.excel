package com.github.curriculeon;

import org.apache.poi.ss.usermodel.Cell;

import java.util.List;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:03 AM
 */
public class ExcelSpreadSheetColumn extends ExcelSpreadSheetTableDataArray {
    public ExcelSpreadSheetColumn(List<Cell> data) {
        super(data);
    }
}