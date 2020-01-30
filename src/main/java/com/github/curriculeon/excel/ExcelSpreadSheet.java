package com.github.curriculeon.excel;

import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetColumn;
import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.excel.tabledata.metadata.ExcelFormula;
import com.github.curriculeon.utils.Transposer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.Arrays;
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
    }

    public String getName() {
        return sheet.getSheetName();
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

    public ExcelSpreadSheetColumn getColumn(Predicate<ExcelSpreadSheetColumn> filterClause) {
        return getColumns()
                .stream()
                .filter(filterClause)
                .findFirst()
                .get();
    }


    public ExcelSpreadSheetRow getRow(Predicate<ExcelSpreadSheetRow> filterClause) {
        return getRows()
                .stream()
                .filter(filterClause)
                .findFirst()
                .get();
    }

    public ExcelSpreadSheetColumn getColumn(String columnName) {
        return getColumns()
                .stream()
                .filter(col -> col.getHeader().equals(columnName))
                .findFirst()
                .get();
    }

    public ExcelSpreadSheetRow getRow(Integer rowNumber) {
        List<Cell> list = new ArrayList<>();
        Row sheetRow = sheet.getRow(rowNumber);
        if (sheetRow == null) {
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
        List<ExcelSpreadSheetColumn> result = new ArrayList<>();
        List<ExcelSpreadSheetRow> rows = getRows();
        for (int rowNumber = 0; rowNumber < rows.size(); rowNumber++) {
            ExcelSpreadSheetRow row =  rows.get(rowNumber);
            List<Cell> cells = row.getData();
            Transposer<Cell> transposer = new Transposer<>(Arrays.asList(cells));
            List<List<Cell>> transposedList = transposer.transpose();
            try {
                List<Cell> columnData = transposedList.get(0);
                Integer columnIndex = columnData.get(0).getColumnIndex();
                ExcelSpreadSheetColumn excelSpreadSheetColumn = new ExcelSpreadSheetColumn(sheet, columnIndex, columnData);
                result.add(excelSpreadSheetColumn);
            } catch(IndexOutOfBoundsException ioobe) {
                continue;
            }
        }
        return result;
    }

    public List<ExcelSpreadSheetRow> getRows() {
        List<ExcelSpreadSheetRow> list = new ArrayList<>();
        IntStream
                .range(0, sheet.getLastRowNum())
                .forEach(i -> list.add(getRow(i)));
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

    public void addRow(ExcelSpreadSheetRow row, int destinationRowNum) {
        for(Cell cell : row) {
            addCell(cell, row.getDimensionIndex(), cell.getColumnIndex());
        }
    }

    public void addCell(Cell cellToClone, int row, int column) {
        ExcelSpreadSheetRow excelSpreadSheetRow = getRow(row);
        String dataToClone = cellToClone.getStringCellValue();
        Cell cellToPopulate = excelSpreadSheetRow.getCell(column);
        cellToPopulate.setCellValue(dataToClone);
    }

    public Integer getSheetIndex() {
        for (int rowNumber = 0; rowNumber < getWorkBook().getNumberOfSheets(); rowNumber++) {
            if(sheet.equals(getWorkBook().getSheetAt(rowNumber))) {
                return rowNumber;
            }
        }
        throw new NullPointerException("Unable to find sheet in workbook");
    }
}
