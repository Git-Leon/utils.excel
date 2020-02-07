package com.github.curriculeon.tests.excel;

import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetColumn;
import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface ExcelSpreadSheetInterface {
    Sheet getSheet();

    default String getName() {
        return getSheet().getSheetName();
    }


    default Workbook getWorkBook() {
        return getSheet().getWorkbook();
    }


    default Cell getCell(Integer row, Integer column) {
        return getCell(row, getColumnName(column));
    }


    default Cell getCell(Integer row, String column) {
        ExcelSpreadSheetRow sheetRow = getRow(row);
        int columnNumber = getColumnNumber(column);
        Cell cell = sheetRow.getCell(columnNumber);
        return cell;
    }


    default Integer getNumberOfRows() {
        return getRows().size();
    }


    default Integer getNumberOfColumns() {
        Integer count = Integer.MIN_VALUE;
        for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
            Row currentRow = getSheet().getRow(rowIndex);
            int numberOfColumnsOnThisRow = currentRow.getPhysicalNumberOfCells();
            if (count < numberOfColumnsOnThisRow) {
                count = numberOfColumnsOnThisRow;
            }
        }
        return count;
    }


    default ExcelSpreadSheetRow getColumnHeaders() {
        return getRow(0);
    }


    default ExcelSpreadSheetColumn getRowHeaders() {
        return getColumn(0);
    }


    default ExcelSpreadSheetColumn getColumn(String columnName) {
        return getColumn(col -> col.getHeader().equals(columnName));
    }



    default ExcelSpreadSheetColumn getColumn(Predicate<ExcelSpreadSheetColumn> filterClause) {
        return getColumns()
                .stream()
                .filter(filterClause)
                .findFirst()
                .get();
    }



    default ExcelSpreadSheetRow getRow(Predicate<ExcelSpreadSheetRow> filterClause) {
        return getRows()
                .stream()
                .filter(filterClause)
                .findFirst()
                .get();
    }


    default ExcelSpreadSheetRow getRow(Integer rowNumber) {
        List<Cell> list = new ArrayList<>();
        Row sheetRow = getSheet().getRow(rowNumber);
        if (sheetRow == null) {
            sheetRow = getSheet().createRow(rowNumber);
        }
        sheetRow.forEach(list::add);
        return new ExcelSpreadSheetRow(getSheet(), rowNumber, list);
    }


    default String getColumnName(Integer columnNumber) {
        StringBuilder res = new StringBuilder();
        while (columnNumber > 0) {
            int index = (columnNumber - 1) % 26;
            res.append((char) (index + 'A'));
            columnNumber = (columnNumber - 1) / 26;
        }

        return res.reverse().toString();
    }


    default int getColumnNumber(String column) {
        int result = 0;
        for (int i = 0; i < column.length(); i++) {
            result *= 26;
            result += column.charAt(i) - 'A' + 1;
        }
        return result;
    }


    default ExcelSpreadSheetColumn getColumn(Integer columnNumber) {
        return getColumn(getColumnName(columnNumber));
    }


    default List<ExcelSpreadSheetColumn> getColumns() {
        List<ExcelSpreadSheetColumn> result = new ArrayList<>();
        int numberOfColumns = getNumberOfColumns();
        int numberOfRows = getNumberOfRows();
        for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
            List<Cell> columnData = new ArrayList<>();
            for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
                Row row = getSheet().getRow(rowIndex);
                Cell cell = row.getCell(colIndex);
                columnData.add(cell);
            }
            result.add(new ExcelSpreadSheetColumn(getSheet(), colIndex, columnData));
        }
        return result;
    }



    default List<ExcelSpreadSheetRow> getRows() {
        List<ExcelSpreadSheetRow> list = new ArrayList<>();
        IntStream
                .range(0, getSheet().getLastRowNum())
                .forEach(rowIndex -> list.add(getRow(rowIndex)));
        return list;
    }


    default List<ExcelSpreadSheetRow> getRowsWhere(Predicate<ExcelSpreadSheetRow> predicate) {
        return new ArrayList<>(getRows()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList()));
    }


    default List<ExcelSpreadSheetColumn> getColumnsWhere(Predicate<ExcelSpreadSheetColumn> predicate) {
        return new ArrayList<>(getColumns()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList()));
    }


    default void addRows(List<ExcelSpreadSheetRow> rows, int startingIndex) {
        for (int i = 0; i < rows.size(); i++) {
            int currentIndex = startingIndex + i;
            ExcelSpreadSheetRow row = rows.get(i);
            addRow(row, currentIndex);
        }

    }


    default void addRow(ExcelSpreadSheetRow row, int destinationRowNum) {
        for (Cell cell : row) {
            addCell(cell, destinationRowNum, cell.getColumnIndex());
        }
    }

    default void addColumns(List<ExcelSpreadSheetColumn> columns, int startingIndex) {
        for (int i = 0; i < columns.size(); i++) {
            int currentIndex = startingIndex + i;
            ExcelSpreadSheetColumn column = columns.get(i);
            addColumn(column, currentIndex);
        }
    }


    default void addColumn(ExcelSpreadSheetColumn column, int destinationColumnNumber) {
        for (Cell cell : column) {
            addCell(cell, cell.getRowIndex(), destinationColumnNumber);
        }
    }


    default void addCell(Cell cellToClone, int row, int column) {
        ExcelSpreadSheetRow excelSpreadSheetRow = getRow(row);
        String dataToClone = cellToClone.getStringCellValue();
        Cell cellToPopulate = excelSpreadSheetRow.getCell(column);
        cellToPopulate.setCellValue(dataToClone);
    }


    default Integer getSheetIndex() {
        for (int rowNumber = 0; rowNumber < getWorkBook().getNumberOfSheets(); rowNumber++) {
            if (getSheet().equals(getWorkBook().getSheetAt(rowNumber))) {
                return rowNumber;
            }
        }
        throw new NullPointerException("Unable to find sheet in workbook");
    }
}