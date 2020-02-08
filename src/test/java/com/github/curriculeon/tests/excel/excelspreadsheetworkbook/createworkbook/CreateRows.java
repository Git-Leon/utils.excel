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
                .append(System.nanoTime())
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
                Arrays.asList("a", "4"),
                Arrays.asList("b", "5"),
                Arrays.asList("c", "6"),
                Arrays.asList("d", "7"));
        System.out.println(workBookFile);
        workBookFile.flush();
    }
}
