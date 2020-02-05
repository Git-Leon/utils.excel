package com.github.curriculeon.tests.excel.excelspreadsheet;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetFileFactory;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetColumn;
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
        int preAddNumberOfSpreadSheets = workBook.getExcelSpreadSheets().size();
        int expectedPostAddNumberOfSpreadSheets = preAddNumberOfSpreadSheets + 1;
        Assert.assertFalse(workBook.containsSheet(sheetName));

        // when
        ExcelSpreadSheet sheet = workBook.createExcelSpreadSheetByName(sheetName);
        workBook.setActive(sheet.getSheet());
        workBook.flush();
        int actualPostAddNumberOfSpreadSheets = workBook.getExcelSpreadSheets().size();
        for(ExcelSpreadSheet spreadSheet : workBook) {
            for(ExcelSpreadSheetColumn column : spreadSheet.getColumns()) {
                System.out.println(column.getHeader());
            }
        }
        // then
        Assert.assertTrue(workBook.containsSheet(sheet.getSheet()));
        Assert.assertTrue(workBook.containsSheet(sheetName));
        Assert.assertEquals(expectedPostAddNumberOfSpreadSheets, actualPostAddNumberOfSpreadSheets);
    }
}