package com.github.curriculeon;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:20 AM
 * Styles an individual cell on an excel spread sheet
 */
public class ExcelSpreadSheetCellStyle {
    private CellStyle cellStyle;


    public ExcelSpreadSheetCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

}