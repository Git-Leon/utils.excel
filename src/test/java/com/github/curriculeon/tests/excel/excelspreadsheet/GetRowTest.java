package com.github.curriculeon.tests.excel.excelspreadsheet;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.utils.io.DirectoryReference;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;

public class GetRowTest {
    @Test
    public void test() {
        File spreadSheetFile = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBookFile(spreadSheetFile);
        for (ExcelSpreadSheet spreadSheet : excelSpreadSheetWorkBook) {
            ExcelSpreadSheetRow row = spreadSheet.getRow(0);
            Iterator<Cell> iterator = row.iterator();
            for (int i = 0; iterator.hasNext(); i++) {
                Cell cell = iterator.next();
                System.out.println(cell.getStringCellValue());
            }
        }
        excelSpreadSheetWorkBook.close();
    }
}
