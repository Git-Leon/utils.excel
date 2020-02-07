package com.github.curriculeon.tests.excel.excelspreadsheet;

import com.github.curriculeon.tests.excel.ExcelSpreadSheetFileFactory;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leon on 1/30/2020.
 */
public class GetExcelSpreadSheetByNameTest {
    private void test(String sheetName) {
        // given
        ExcelSpreadSheetWorkBookFile workBook = ExcelSpreadSheetFileFactory.getMockData();
        Sheet expected = workBook.createExcelSpreadSheetByName(sheetName).getSheet();

        // when
        Sheet actual = workBook.getExcelSpreadSheetByName(sheetName).get();

        Assert.assertEquals(expected, actual);
        workBook.close();
    }

    private void test(int stringLength) {
        test(new String(new char[stringLength]).replaceAll("\0", "_"));
    }

    // tests behavior of variable string-lengths; spread sheet names cannot be greater than 32 characters in length
    @Test
    public void test0() {
        for (int i = 1; i < 32; i++) {
            test(i);
        }
    }
}