package com.github.curriculeon.tests.excel.excelspreadsheet;

import com.github.curriculeon.tests.excel.ExcelSpreadSheet;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.tests.excel.tabledata.ExcelSpreadSheetRow;
import com.github.curriculeon.tests.excel.tabledata.metadata.CellTypeAdapter;
import com.github.curriculeon.utils.io.DirectoryReference;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;

import java.io.File;

/**
 * @author leonhunter
 * @created 02/07/2020 - 12:47 PM
 */
public class PrintRowsNeatlyTest {
    @Test
    public void test() {
        File spreadSheetFile = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBookFile(spreadSheetFile);
        StringBuilder sb = new StringBuilder();
        for (ExcelSpreadSheet spreadSheet : excelSpreadSheetWorkBook) {
            for (ExcelSpreadSheetRow row : spreadSheet.getRows()) {
                for(Cell cell : row) {
                    sb.append(String.format("[ %s ]", CellTypeAdapter.getCellValue(cell)));
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
        excelSpreadSheetWorkBook.close();
    }
}
