package com.github.curriculeon.tests.excel.excelspreadsheetworkbook.createworkbook;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetFileFactory;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.tests.excel.tabledata.cell.metadata.CellTypeAdapter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author leonhunter
 * @created 02/07/2020 - 5:13 PM
 */
public class CreateRows {
    @Test
    public void test() {
        // given
        ExcelSpreadSheetWorkBookFile workBookFile = ExcelSpreadSheetFileFactory.getNewExcelWorkBookFile();
        ExcelSpreadSheet sheet = workBookFile.createNewExcelSpreadSheet(Long.toHexString(System.nanoTime()));

        // when
        sheet.addRows(
                Arrays.asList("ID", "Age"),
                Arrays.asList("a", "4"),
                Arrays.asList("b", "5"),
                Arrays.asList("c", "6"),
                Arrays.asList("d", "7"));
        workBookFile.flush();

        // Then :
        // fetch first column data
        String actualRow0Column0 = CellTypeAdapter.getCellValue(sheet.getCell(0, 0 ));
        String actualRow1Column0 = CellTypeAdapter.getCellValue(sheet.getCell(1, 0 ));
        String actualRow2Column0 = CellTypeAdapter.getCellValue(sheet.getCell(2, 0 ));
        String actualRow3Column0 = CellTypeAdapter.getCellValue(sheet.getCell(3, 0 ));
        String actualRow4Column0 = CellTypeAdapter.getCellValue(sheet.getCell(4, 0 ));

        // compare first column data
        Assert.assertEquals("ID", actualRow0Column0);
        Assert.assertEquals("a", actualRow1Column0);
        Assert.assertEquals("b", actualRow2Column0);
        Assert.assertEquals("c", actualRow3Column0);
        Assert.assertEquals("d", actualRow4Column0);

        // fetch second column data
        String actualRow0Column1 = CellTypeAdapter.getCellValue(sheet.getCell(0, 1 ));
        String actualRow1Column1 = CellTypeAdapter.getCellValue(sheet.getCell(1, 1 ));
        String actualRow2Column1 = CellTypeAdapter.getCellValue(sheet.getCell(2, 1 ));
        String actualRow3Column1 = CellTypeAdapter.getCellValue(sheet.getCell(3, 1 ));
        String actualRow4Column1 = CellTypeAdapter.getCellValue(sheet.getCell(4, 1 ));


        // compare second column data
        Assert.assertEquals("Age", actualRow0Column1);
        Assert.assertEquals("4", actualRow1Column1);
        Assert.assertEquals("5", actualRow2Column1);
        Assert.assertEquals("6", actualRow3Column1);
        Assert.assertEquals("7", actualRow4Column1);
    }
}