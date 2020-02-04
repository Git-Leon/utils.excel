package com.github.curriculeon.tests.excel.excelspreadsheet;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetColumn;
import com.github.curriculeon.tests.excel.tabledata.metadata.CellTypeAdapter;
import com.github.curriculeon.utils.ResourceUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;

public class GetColumnTest {
    @Test
    public void test() {
        File spreadSheetFile = ResourceUtils.getResourceFile("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBookFile(spreadSheetFile);
        for (ExcelSpreadSheet spreadSheet : excelSpreadSheetWorkBook) {
            for (ExcelSpreadSheetColumn column : spreadSheet.getColumns()) {
                for (Cell cell : column) {
                    System.out.println("\n\n-----------------");
                    System.out.println("\t" + cell );
                    System.out.println("-----------------");
                }
            }
        }
    }
}
