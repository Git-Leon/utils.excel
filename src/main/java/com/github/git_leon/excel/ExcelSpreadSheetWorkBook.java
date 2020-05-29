package com.github.git_leon.excel;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author leonhunter
 * @created 01/24/2020 - 10:35 PM
 */
public class ExcelSpreadSheetWorkBook implements ExcelSpreadSheetWorkBookInterface {
    private final Workbook workbook;

    public ExcelSpreadSheetWorkBook(Workbook workbook) {
        this.workbook = workbook;
    }

    @Override
    public String toString() {
        return "ExcelSpreadSheetWorkBook{" +
                "workbook=" + getWorkBook() +
                ", sheets=" + getExcelSpreadSheets() +
                '}';
    }

    @Override
    public Workbook getWorkBook() {
        return workbook;
    }
}
