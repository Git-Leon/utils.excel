package com.github.curriculeon.tests.excel;

import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetColumn;
import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.tests.excel.tabledata.metadata.CellTypeAdapter;
import com.github.curriculeon.tests.excel.tabledata.metadata.ExcelFormula;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.*;
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

    public Integer getNumberOfRows() {
        return getRows().size();
    }

    public Integer getNumberOfColumns() {
        Integer count = Integer.MIN_VALUE;
        for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
            Row currentRow = sheet.getRow(rowIndex);
            int numberOfColumnsOnThisRow = currentRow.getPhysicalNumberOfCells();
            if (count < numberOfColumnsOnThisRow) {
                count = numberOfColumnsOnThisRow;
            }
        }
        return count;
    }

    public List<ExcelSpreadSheetColumn> toListOfExcelSpreadSheetColumns(List<List<String>> columnData) {
        List<ExcelSpreadSheetColumn> result = new ArrayList<>();
        List<List<Cell>> columns = CellTypeAdapter.toListOfCellLists(getSheet(), columnData);
        int numberOfColumns = getNumberOfColumns();
        for (int i = 0; i < columns.size(); i++) {
            int columnIndex = numberOfColumns + i;
            List<Cell> column = columns.get(i);
            ExcelSpreadSheetColumn spreadSheetColumn = new ExcelSpreadSheetColumn(getSheet(), columnIndex, column);
            result.add(spreadSheetColumn);
        }
        return result;
    }

    public ExcelSpreadSheetRow getColumnHeaders() {
        return getRow(0);
    }

    public ExcelSpreadSheetColumn getRowHeaders() {
        return getColumn(0);
    }

    public ExcelSpreadSheetColumn getColumn(String columnName) {
        return getColumn(col -> col.getHeader().equals(columnName));
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
        int numberOfColumns = getNumberOfColumns();
        int numberOfRows = getNumberOfRows();
        for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
            List<Cell> columnData = new ArrayList<>();
            for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                Cell cell = row.getCell(colIndex);
                columnData.add(cell);
            }
            result.add(new ExcelSpreadSheetColumn(sheet, colIndex, columnData));
        }
        return result;
    }


    public List<ExcelSpreadSheetRow> getRows() {
        List<ExcelSpreadSheetRow> list = new ArrayList<>();
        IntStream
                .range(0, sheet.getLastRowNum())
                .forEach(rowIndex -> list.add(getRow(rowIndex)));
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

    public void addRows(List<ExcelSpreadSheetRow> rows, int startingIndex) {
        for (int i = 0; i < rows.size(); i++) {
            int currentIndex = startingIndex + i;
            ExcelSpreadSheetRow row = rows.get(i);
            addRow(row, currentIndex);
        }

    }

    public void addRow(ExcelSpreadSheetRow row, int destinationRowNum) {
        for (Cell cell : row) {
            addCell(cell, destinationRowNum, cell.getColumnIndex());
        }
    }
    public void addColumns(List<ExcelSpreadSheetColumn> columns, int startingIndex) {
        for (int i = 0; i < columns.size(); i++) {
            int currentIndex = startingIndex + i;
            ExcelSpreadSheetColumn column = columns.get(i);
            addColumn(column, currentIndex);
        }
    }

    public void addColumn(ExcelSpreadSheetColumn column, int destinationColumnNumber) {
        for (Cell cell : column) {
            addCell(cell, cell.getRowIndex(), destinationColumnNumber);
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
            if (sheet.equals(getWorkBook().getSheetAt(rowNumber))) {
                return rowNumber;
            }
        }
        throw new NullPointerException("Unable to find sheet in workbook");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        getRows().forEach(r -> sb.append(r.toString()));
        return sb.toString();
    }
}
