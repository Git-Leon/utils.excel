package com.github.curriculeon.tests.excel.excelspreadsheetworkbook.createworkbook;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.utils.io.DirectoryReference;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author leonhunter
 * @created 02/07/2020 - 5:13 PM
 */
public class CreateRows {
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
        sheet.addRows(
                Arrays.asList("ID", "Age"),
                Arrays.asList("0", "4"),
                Arrays.asList("1", "5"),
                Arrays.asList("2", "6"),
                Arrays.asList("3", "7"));
        workBookFile.flush();
    }
}
