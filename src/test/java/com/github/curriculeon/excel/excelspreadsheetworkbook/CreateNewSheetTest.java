package com.github.curriculeon.excel.excelspreadsheetworkbook;

import com.github.curriculeon.excel.DeprecatedExcelSpreadSheetWorkBook;
import com.github.curriculeon.excel.ExcelSpreadSheetFileFactory;
import org.junit.Assert;
import org.junit.Test;

public class CreateNewSheetTest {
    // given
    private void test(String sheetName) {
        DeprecatedExcelSpreadSheetWorkBook workBook = ExcelSpreadSheetFileFactory.getMockData();
        Assert.assertTrue(workBook.getExcelSpreadSheetByName(sheetName) == null);

        // when
        workBook.addSheet(workBook.getExcelSpreadSheetByIndex(0).get().getSheet(), sheetName);

        // then
        Assert.assertFalse(workBook.getExcelSpreadSheetByName(sheetName) == null);
    }


    @Test
    public void test1() {
        test("The quick brown fox jumps over the lazy dog");
    }
}
