package com.github.curriculeon.excel.excelspreadsheet;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBook;
import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.utils.ResourceUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;

public class GetRowTest {
    @Test
    public void test() {
        File spreadSheetFile = ResourceUtils.getResourceFile("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBook excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBook(spreadSheetFile);
        for (ExcelSpreadSheet spreadSheet : excelSpreadSheetWorkBook) {
            ExcelSpreadSheetRow row = spreadSheet.getRow(0);
            Iterator<Cell> iterator = row.iterator();
            for (int i = 0; iterator.hasNext(); i++) {
                Cell cell = iterator.next();
                System.out.println(cell.getStringCellValue());
            }
        }
    }
}
