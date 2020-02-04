package com.github.curriculeon.tests.excel.excelspreadsheet;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.utils.ResourceUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;

import java.io.File;

public class GetRowsTest {
    @Test
    public void test() {
        File spreadSheetFile = ResourceUtils.getResourceFile("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBookFile(spreadSheetFile);
        for (ExcelSpreadSheet spreadSheet : excelSpreadSheetWorkBook) {
            for (ExcelSpreadSheetRow row : spreadSheet.getRows()) {
                for(Cell cell : row) {
                    System.out.println(cell.toString());
                }
            }
        }
    }
}
