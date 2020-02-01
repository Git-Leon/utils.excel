package com.github.curriculeon.excel.excelspreadsheetworkbook;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetFileFactory;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBookFile;
import org.junit.Assert;
import org.junit.Test;

public class CreateNewSheetTest {
    // given
    private void test(String sheetName) {
        ExcelSpreadSheetWorkBookFile workBook = ExcelSpreadSheetFileFactory.getMockData();
        Assert.assertFalse(workBook.getExcelSpreadSheetByName(sheetName).isPresent());

        // when
        ExcelSpreadSheet newSheet = workBook.createNewExcelSpreadSheet(sheetName);
        workBook.setSheetOrder(newSheet.getName(), 0);
        workBook.flush();

        // then
        Assert.assertNotNull(newSheet);
    }


    @Test
    public void test1() {
        test("The quick brown fox jumps over the lazy dog");
    }
}
