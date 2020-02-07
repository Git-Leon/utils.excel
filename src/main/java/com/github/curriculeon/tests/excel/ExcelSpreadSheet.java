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
        getRows().forEach(r -> sb.append(r.toString()));
        return sb.toString();
    }
}
