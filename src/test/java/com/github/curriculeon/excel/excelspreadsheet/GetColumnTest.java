package com.github.curriculeon.excel.excelspreadsheet;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.DeprecatedExcelSpreadSheetWorkBook;
import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetColumn;
import com.github.curriculeon.utils.ResourceUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;

public class GetColumnTest {
    @Test
    public void test() {
        File spreadSheetFile = ResourceUtils.getResourceFile("java-developer-philly-rubric-template.xlsx");
        DeprecatedExcelSpreadSheetWorkBook excelSpreadSheetWorkBook = new DeprecatedExcelSpreadSheetWorkBook(spreadSheetFile);
        for(ExcelSpreadSheet spreadSheet : excelSpreadSheetWorkBook) {
            ExcelSpreadSheetColumn column = spreadSheet.getColumn(0);
            System.out.println(column);
            Iterator<Cell> iterator = column.iterator();
            for (int i = 0; iterator.hasNext(); i++) {
                 Cell cell = iterator.next();
                System.out.println(cell.getStringCellValue());
            }
        }
    }
}
