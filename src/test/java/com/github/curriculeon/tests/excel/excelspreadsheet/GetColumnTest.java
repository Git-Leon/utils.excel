package com.github.curriculeon.tests.excel.excelspreadsheet;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetColumn;
import com.github.curriculeon.utils.io.DirectoryReference;
import org.apache.poi.ss.usermodel.Cell;
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
                for (Cell cell : column) {
                    output
                            .append("\n\n-----------------")
                            .append("\t" + cell )
                            .append("\n-----------------");
                }
            }
        }
        System.out.println(output);
        excelSpreadSheetWorkBook.close();
    }
}
