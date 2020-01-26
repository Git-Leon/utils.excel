package com.github.curriculeon.excel;

import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetColumn;
import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.excel.tabledata.metadata.ExcelFormula;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author leonhunter
 * @created 01/24/2020 - 10:44 PM
 */
public class ExcelSpreadSheet {
    private final Sheet sheet;

    public ExcelSpreadSheet(Sheet sheet) {
        this.sheet = sheet;
        if(sheet == null) {
            throw new NullPointerException();
        }
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
        ExcelSpreadSheetRow sheetRow = getRow(row);
        int columnNumber = getColumnNumber(column);
        Cell cell = sheetRow.getCell(columnNumber);
        return cell;
    }

    public ExcelSpreadSheetColumn getColumn(String columnName) {
        List<Cell> columnData = new ArrayList<>();
        getRows().stream().forEach(row -> columnData.add(row.getCell(getColumnNumber(columnName))));
        int columnNumber = getColumnNumber(columnName);
        return new ExcelSpreadSheetColumn(sheet, columnNumber, columnData);
    }

    public ExcelSpreadSheetRow getRow(Integer rowNumber) {
        List<Cell> list = new ArrayList<>();
        Row sheetRow = sheet.getRow(rowNumber);
        if(sheetRow == null) {
            sheetRow = sheet.createRow(rowNumber);
        }
        sheetRow.forEach(list::add);
        return new ExcelSpreadSheetRow(sheet, rowNumber, list);
    }

    public String getColumnName(Integer columnNumber) {
        StringBuilder res = new StringBuilder();
        while (columnNumber > 0) {
            int index = (columnNumber - 1) % 26;
            res.append((char) (index + 'A'));
            columnNumber = (columnNumber - 1) / 26;
        }

        return res.reverse().toString();
    }

    public int getColumnNumber(String column) {
        int result = 0;
        for (int i = 0; i < column.length(); i++) {
            result *= 26;
            result += column.charAt(i) - 'A' + 1;
        }
        return result;
    }

    public ExcelSpreadSheetColumn getColumn(Integer columnNumber) {
        return getColumn(getColumnName(columnNumber));
    }

    public List<ExcelSpreadSheetColumn> getColumns() {
        return null;
    }

    public List<ExcelSpreadSheetRow> getRows() {
        List<ExcelSpreadSheetRow> list = new ArrayList<>();
        IntStream
                .range(0,sheet.getLastRowNum())
                .forEach(i-> list.add(getRow(i)));
        return list;
    }

    public List<ExcelSpreadSheetRow> getRowsWhere(Predicate<ExcelSpreadSheetRow> predicate) {
        return new ArrayList<>(getRows()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList()));
    }
    public List<ExcelSpreadSheetColumn> getColumnsWhere(Predicate<ExcelSpreadSheetColumn> predicate) {
        return new ArrayList<>(getColumns()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList()));
    }
}
