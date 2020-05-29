package com.github.git_leon.excel.excelspreadsheet;

import com.github.git_leon.excel.ExcelSpreadSheet;
import com.github.git_leon.excel.ExcelSpreadSheetWorkBookFile;
import com.github.git_leon.excel.tabledata.dataarray.ExcelSpreadSheetColumn;
import com.github.git_leon.utils.io.DirectoryReference;
import org.junit.Test;

import java.io.File;

public class GetColumnTest {
    @Test
    public void test() {
        File spreadSheetFile = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBookFile(spreadSheetFile);
        StringBuilder output = new StringBuilder();
        for (ExcelSpreadSheet spreadSheet : excelSpreadSheetWorkBook) {
            for (ExcelSpreadSheetColumn column : spreadSheet.getColumns()) {
                System.out.println(column);
            }
        }
        System.out.println(output);
        excelSpreadSheetWorkBook.close();
    }
}
