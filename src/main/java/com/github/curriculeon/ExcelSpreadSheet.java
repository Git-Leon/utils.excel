package com.github.curriculeon;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * @author leonhunter
 * @created 01/24/2020 - 10:44 PM
 */
public class ExcelSpreadSheet {
    private final Sheet sheet;

    public ExcelSpreadSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public void setFormula(ExcelFormula formula, Cell cell) {

    }

    public Workbook getWorkBook() {
        return sheet.getWorkbook();
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setFormula(ExcelFormula formula, Integer columnNumber) {
        getColumn(columnNumber).setCellFormula(formula.toString());
    }

    public Cell getCell(Integer row, Integer column) {
        return getCell(row, getColumnName(column));
    }

    public Cell getCell(Integer row, String column) {
        return null;
    }

    public String getColumnName(Integer columnNumber) {
        StringBuilder res = new StringBuilder();
        while (columnNumber > 0) {
            int index = (columnNumber - 1) % 26;
            res.append((char)(index + 'A'));
            columnNumber = (columnNumber - 1) / 26;
        }

        return res.reverse().toString();
    }

    public ExcelSpreadSheetRow getRow(Integer rowNumber) {
        return null;
    }

    public ExcelSpreadSheetColumn getColumn(String columnName) {
        return null;
    }

    private ExcelSpreadSheetColumn getColumn(Integer columnNumber) {
        return getColumn(getColumnName(columnNumber));
    }

    public List<ExcelSpreadSheetColumn> getColumns() {
        return null;
    }

    public List<ExcelSpreadSheetRow> getRows() {
        return null;
    }
}
