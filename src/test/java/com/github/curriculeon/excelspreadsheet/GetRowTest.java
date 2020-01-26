package com.github.curriculeon.excelspreadsheet;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetFile;
import com.github.curriculeon.excel.tabledata.ExcelSpreadSheetColumn;
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
        ExcelSpreadSheetFile excelSpreadSheetFile = new ExcelSpreadSheetFile(spreadSheetFile);
        for (ExcelSpreadSheet spreadSheet : excelSpreadSheetFile) {
            ExcelSpreadSheetRow row = spreadSheet.getRow(0);
            Iterator<Cell> iterator = row.iterator();
            for (int i = 0; iterator.hasNext(); i++) {
                Cell cell = iterator.next();
                System.out.println(cell.getStringCellValue());
            }
        }
    }
}
