package com.github.curriculeon.excel.excelspreadsheet;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBook;
import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.utils.ResourceUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;

import java.io.File;

public class GetRowsTest {
    @Test
    public void test() {
        File spreadSheetFile = ResourceUtils.getResourceFile("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBook excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBook(spreadSheetFile);
        for (ExcelSpreadSheet spreadSheet : excelSpreadSheetWorkBook) {
            for (ExcelSpreadSheetRow row : spreadSheet.getRows()) {
                for(Cell cell : row) {
                    System.out.println(cell.toString());
                }
            }
        }
    }
}
