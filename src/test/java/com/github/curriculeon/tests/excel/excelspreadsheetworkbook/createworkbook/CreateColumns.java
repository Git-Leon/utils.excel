package com.github.curriculeon.tests.excel.excelspreadsheetworkbook.createworkbook;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetFileFactory;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author leonhunter
 * @created 02/07/2020 - 5:13 PM
 */
public class CreateColumns {
    @Test
    public void test() {
        ExcelSpreadSheetWorkBookFile workBookFile = ExcelSpreadSheetFileFactory.getNewExcelWorkBookFile();
        ExcelSpreadSheet sheet = workBookFile.createNewExcelSpreadSheet(Long.toHexString(System.nanoTime()));
        sheet.addColumns(
                Arrays.asList("ID", "0", "1", "2", "3"),
                Arrays.asList("Age", "4", "5", "6", "7"));
        workBookFile.flush();
    }
}
