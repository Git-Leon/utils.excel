package com.github.git_leon.excel.excelspreadsheetworkbook.createworkbook;

import com.github.git_leon.excel.ExcelSpreadSheetFileFactory;
import com.github.git_leon.excel.ExcelSpreadSheet;
import com.github.git_leon.excel.ExcelSpreadSheetWorkBookFile;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author leonhunter
 * @created 02/07/2020 - 5:13 PM
 */
public class CreateRows {
    @Test
    public void test1() {
        // given
        ExcelSpreadSheetWorkBookFile workBookFile = ExcelSpreadSheetFileFactory.getNewExcelWorkBookFile();
        ExcelSpreadSheet sheet = workBookFile.createNewExcelSpreadSheet(Long.toHexString(System.nanoTime()));

        // when
        sheet.addRows(
                Arrays.asList("ID"),
                Arrays.asList("0"),
                Arrays.asList("1"),
                Arrays.asList("2"));
        workBookFile.flush();

        // Then :
        // fetch first column data
        String actualRow0Column0 = sheet.getCell(0, 0).getCellValue();
        Assert.assertEquals("ID", actualRow0Column0);

        String actualRow1Column0 = sheet.getCell(1, 0).getCellValue();
        Assert.assertEquals("0", actualRow1Column0);

        String actualRow2Column0 = sheet.getCell(2, 0).getCellValue();
        Assert.assertEquals("1", actualRow2Column0);

        String actualRow3Column0 = sheet.getCell(3, 0).getCellValue();
        Assert.assertEquals("2", actualRow3Column0);
    }

    @Test
    public void test2() {
        // given
        ExcelSpreadSheetWorkBookFile workBookFile = ExcelSpreadSheetFileFactory.getNewExcelWorkBookFile();
        ExcelSpreadSheet sheet = workBookFile.createNewExcelSpreadSheet(Long.toHexString(System.nanoTime()));

        // when
        sheet.addRows(
                Arrays.asList("ID", "Age", "Name"),
                Arrays.asList("0", "99", "Leon"),
                Arrays.asList("1", "98", "Christopher"),
                Arrays.asList("2", "97", "Hunter"));
        workBookFile.flush();

        // Then :
        // fetch first column data
        String actualRow0Column0 = sheet.getCell(0, 0).getCellValue();
        Assert.assertEquals("ID", actualRow0Column0);

        String actualRow1Column0 = sheet.getCell(1, 0).getCellValue();
        Assert.assertEquals("0", actualRow1Column0);

        String actualRow2Column0 = sheet.getCell(2, 0).getCellValue();
        Assert.assertEquals("1", actualRow2Column0);

        String actualRow3Column0 = sheet.getCell(3, 0).getCellValue();
        Assert.assertEquals("2", actualRow3Column0);

        // Then :
        // fetch second column data
        String actualRow0Column1 = sheet.getCell(0, 1).getCellValue();
        Assert.assertEquals("Age", actualRow0Column1);

        String actualRow1Column1 = sheet.getCell(1, 1).getCellValue();
        Assert.assertEquals("99", actualRow1Column1);

        String actualRow2Column1 = sheet.getCell(2, 1).getCellValue();
        Assert.assertEquals("98", actualRow2Column1);

        String actualRow3Column1 = sheet.getCell(3, 1).getCellValue();
        Assert.assertEquals("97", actualRow3Column1);


        // Then :
        // fetch third column data
        String actualRow0Column2 = sheet.getCell(0, 2).getCellValue();
        Assert.assertEquals("Name", actualRow0Column2);

        String actualRow1Column2 = sheet.getCell(1, 2).getCellValue();
        Assert.assertEquals("Leon", actualRow1Column2);

        String actualRow2Column2 = sheet.getCell(2, 2).getCellValue();
        Assert.assertEquals("Christopher", actualRow2Column2);

        String actualRow3Column2 = sheet.getCell(3, 2).getCellValue();
        Assert.assertEquals("Hunter", actualRow3Column2);
    }


    @Test
    public void test3() {
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
        String actualRow0Column0 = sheet.getCell(0, 0 ).getCellValue();
        String actualRow1Column0 = sheet.getCell(1, 0 ).getCellValue();
        String actualRow2Column0 = sheet.getCell(2, 0 ).getCellValue();
        String actualRow3Column0 = sheet.getCell(3, 0 ).getCellValue();
        String actualRow4Column0 = sheet.getCell(4, 0 ).getCellValue();

        // compare first column data
        Assert.assertEquals("ID", actualRow0Column0);
        Assert.assertEquals("a", actualRow1Column0);
        Assert.assertEquals("b", actualRow2Column0);
        Assert.assertEquals("c", actualRow3Column0);
        Assert.assertEquals("d", actualRow4Column0);

        // fetch second column data
        String actualRow0Column1 = sheet.getCell(0, 1 ).getCellValue();
        String actualRow1Column1 = sheet.getCell(1, 1 ).getCellValue();
        String actualRow2Column1 = sheet.getCell(2, 1 ).getCellValue();
        String actualRow3Column1 = sheet.getCell(3, 1 ).getCellValue();
        String actualRow4Column1 = sheet.getCell(4, 1 ).getCellValue();


        // compare second column data
        Assert.assertEquals("Age", actualRow0Column1);
        Assert.assertEquals("4", actualRow1Column1);
        Assert.assertEquals("5", actualRow2Column1);
        Assert.assertEquals("6", actualRow3Column1);
        Assert.assertEquals("7", actualRow4Column1);
    }
}
