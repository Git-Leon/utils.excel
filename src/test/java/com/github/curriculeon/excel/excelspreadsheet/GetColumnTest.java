package com.github.curriculeon.excel.excelspreadsheet;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.excel.tabledata.dataarray.ExcelSpreadSheetColumn;
import com.github.curriculeon.utils.io.DirectoryReference;
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
