package com.github.curriculeon.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:00 AM
 */
public class ExcelSpreadSheetRow extends ExcelSpreadSheetTableDataArray {
    public ExcelSpreadSheetRow(List<Cell> data) {
        super(data);
    }
}
