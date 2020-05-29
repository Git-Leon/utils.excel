package com.github.git_leon.excel.excelspreadsheet;

import com.github.git_leon.excel.ExcelSpreadSheet;
import com.github.git_leon.excel.ExcelSpreadSheetWorkBookFile;
import com.github.git_leon.excel.tabledata.dataarray.ExcelSpreadSheetRow;
import com.github.git_leon.utils.io.DirectoryReference;
import org.junit.Test;

import java.io.File;

public class GetRowTest {
    @Test
    public void test() {
        File spreadSheetFile = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBookFile(spreadSheetFile);
        for (ExcelSpreadSheet spreadSheet : excelSpreadSheetWorkBook) {
            ExcelSpreadSheetRow row = spreadSheet.getRow(0);
            System.out.println(row);
        }
        excelSpreadSheetWorkBook.close();
    }
}
