package com.github.git_leon.excel;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author leonhunter
 * @created 01/24/2020 - 10:44 PM
 */
public class ExcelSpreadSheet implements ExcelSpreadSheetInterface {
    private final Sheet sheet;

    public ExcelSpreadSheet(Sheet sheet) {
        this.sheet = sheet;
    }


    public Sheet getSheet() {
        return sheet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        getRows().forEach(row -> sb.append(row.toString()));
        return sb.toString();
    }
}
