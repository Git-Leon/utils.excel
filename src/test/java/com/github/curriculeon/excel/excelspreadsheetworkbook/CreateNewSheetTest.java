package com.github.curriculeon.excel.excelspreadsheetworkbook;

import com.github.curriculeon.excel.ExcelSpreadSheetWorkBook;
import com.github.curriculeon.excel.ExcelSpreadSheetFileFactory;
import org.junit.Assert;
import org.junit.Test;

public class CreateNewSheetTest {
    // given
    private void test(String sheetName) {
        ExcelSpreadSheetWorkBook workBook = ExcelSpreadSheetFileFactory.getMockData();
        Assert.assertFalse(workBook.getExcelSpreadSheetByName(sheetName).isPresent());

        // when
        workBook.addSheet(workBook.getExcelSpreadSheetByIndex(0).get().getSheet(), sheetName);

        // then
        Assert.assertTrue(workBook.getExcelSpreadSheetByName(sheetName).isPresent());
    }


    @Test
    public void test1() {
        test("The quick brown fox jumps over the lazy dog");
    }
}
