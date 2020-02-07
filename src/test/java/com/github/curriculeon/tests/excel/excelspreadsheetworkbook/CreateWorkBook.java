package com.github.curriculeon.tests.excel.excelspreadsheetworkbook;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetColumn;
import com.github.curriculeon.tests.excel.tabledata.metadata.CellTypeAdapter;
import com.github.curriculeon.utils.io.DirectoryReference;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author leonhunter
 * @created 02/07/2020 - 5:13 PM
 */
public class CreateWorkBook {
    @Test
    public void test() {
        // given
        File file = DirectoryReference.TARGETDIRECTORY.getFileFromDirectory(new StringBuilder()
                .append("test")
                .append(Long.toHexString(System.nanoTime()))
                .append(".xlsx")
                .toString());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new Error(e);
            }
        }
        ExcelSpreadSheetWorkBookFile workBookFile = new ExcelSpreadSheetWorkBookFile(file);
        ExcelSpreadSheet sheet = workBookFile.createNewExcelSpreadSheet(Long.toHexString(System.nanoTime()));
//        sheet.addColumns(
//                Arrays.asList("Hello", "World"),
//                Arrays.asList("Hey", "There"));
        sheet.addColumns(Arrays.asList(new ExcelSpreadSheetColumn(sheet.getSheet(), 0, CellTypeAdapter.toCellList(sheet.getSheet(), Arrays.asList("Hello world")))), 0);
        workBookFile.flush();
    }
}
