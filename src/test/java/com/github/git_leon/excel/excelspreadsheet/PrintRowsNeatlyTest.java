package com.github.git_leon.excel.excelspreadsheet;

import com.github.git_leon.excel.ExcelSpreadSheet;
import com.github.git_leon.excel.ExcelSpreadSheetWorkBookFile;
import com.github.git_leon.excel.tabledata.cell.metadata.CellTypeAdapter;
import com.github.git_leon.excel.tabledata.dataarray.ExcelSpreadSheetRow;
import com.github.git_leon.utils.io.DirectoryReference;
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
                for (Cell cell : row) {
                    sb.append(String.format("[ %s ]", CellTypeAdapter.getCellValue(cell)));
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
        excelSpreadSheetWorkBook.close();
    }
}
