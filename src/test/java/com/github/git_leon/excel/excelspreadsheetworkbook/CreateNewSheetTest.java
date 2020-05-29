package com.github.git_leon.excel.excelspreadsheetworkbook;

import com.github.git_leon.excel.ExcelSpreadSheetFileFactory;
import com.github.git_leon.excel.ExcelSpreadSheet;
import com.github.git_leon.excel.ExcelSpreadSheetWorkBookFile;
import org.junit.Assert;
import org.junit.Test;

public class CreateNewSheetTest {
    // given
    private void test(String sheetName) {
        ExcelSpreadSheetWorkBookFile workBook = ExcelSpreadSheetFileFactory.getCopyOfRubricTemplateWorkBookFile();
        Assert.assertFalse(workBook.getExcelSpreadSheetByName(sheetName).isPresent());

        // when
        ExcelSpreadSheet newSheet = workBook.createNewExcelSpreadSheet(sheetName);
        workBook.setSheetOrder(newSheet.getName(), 0);
        workBook.flush();
        workBook.close();

        // then
        Assert.assertNotNull(newSheet);
    }


    private void test(int stringLength) {
        test(new String(new char[stringLength]).replaceAll("\0", "_"));
    }

    @Test
    public void testLiteral() {
        test("The quick brown fox jumps over the lazy dog");
    }

    @Test
    public void testVariable() {
        for (int i = 1; i < 32; i++) {
            test(i);
        }
    }


}
