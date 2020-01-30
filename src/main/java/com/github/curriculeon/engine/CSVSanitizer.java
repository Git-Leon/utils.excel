package com.github.curriculeon.engine;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.utils.Transposer;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leonhunter
 * @created 01/24/2020 - 11:58 PM
 * The purpose of this class is to be the engine which
 * 1. parses the ugly canvas CSV
 * 2. converts ugly CSV data to pretty CSV
 * (this process is later followed by extracting data from the pretty CSV to a XLSX file)
 */
public class CSVSanitizer {
    private final CSVWriter writer;
    private List<List<String>> rows;

    public CSVSanitizer(File source, File destination) {
        try {
            CSVReader reader = new CSVReader(new FileReader(source.getAbsolutePath()));
            this.writer = new CSVWriter(new FileWriter(destination.getAbsolutePath()));
            this.rows = normalize(reader.readAll());
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    // TODO
    public List<List<String>> parseRows() {
        write();
        return rows;
    }



    public List<String> getRow(Integer columnNumber) {
        return rows.get(columnNumber);
    }

    public List<String> getColumn(Integer columnNumber) {
        return transpose(rows).get(columnNumber);
    }

    public void write() {
        writer.writeAll(standardize());
    }

    private List<List<String>> normalize(List<String[]> rows) {
        return Transposer.normalize(rows);
    }

    private List<String[]> standardize() {
        List<String[]> result = new ArrayList<>();
        for (List<String> column : rows) {
            result.add(column.toArray(new String[0]));
        }
        return result;
    }

    private <T> List<List<T>> transpose(List<List<T>> table) {
        Transposer transposer = new Transposer<>(table);
        return transposer.transpose();
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public List<List<Cell>> parseToSheet(Sheet newSheet) {
        ExcelSpreadSheet newExcelSpreadSheet = new ExcelSpreadSheet(newSheet);
        List<List<String>> rows = this.parseRows();
        List<List<Cell>> cellList = new ArrayList<>();
        for (int rowNumber = 0; rowNumber < rows.size(); rowNumber++) {
            List<String> stringListData = rows.get(rowNumber);
            List<Cell> cellListData = new ArrayList<>();
            for (int columnNumber = 0; columnNumber < stringListData.size(); columnNumber++) {
                String cellValue = stringListData.get(columnNumber);
                Cell cell = newExcelSpreadSheet.getCell(rowNumber, columnNumber);
                cell.setCellValue(cellValue);
                cellListData.add(cell);
            }
            ExcelSpreadSheetRow row = new ExcelSpreadSheetRow(newSheet, rowNumber, cellListData);
            newExcelSpreadSheet.addRow(row, row.getDimensionIndex());
        }
        return cellList;
    }
}

