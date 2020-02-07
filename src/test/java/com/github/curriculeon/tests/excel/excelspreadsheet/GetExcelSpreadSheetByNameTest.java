package com.github.curriculeon.tests.excel.excelspreadsheet;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetFileFactory;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetColumn;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leon on 1/30/2020.
 */
public class GetExcelSpreadSheetByNameTest {
    @Test
    public void test() {
        // given
        String sheetName = "_";
        ExcelSpreadSheetWorkBookFile workBook = ExcelSpreadSheetFileFactory.getMockData();
        Sheet expected = workBook.createExcelSpreadSheetByName(sheetName).getSheet();

        // when
        Sheet actual = workBook.getExcelSpreadSheetByName(sheetName).get();

        Assert.assertEquals(expected, actual);
        workBook.close();
    }
}
