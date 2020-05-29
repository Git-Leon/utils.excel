package com.github.git_leon.excel.excelspreadsheetworkbook;

import com.github.git_leon.excel.tabledata.dataarray.ExcelSpreadSheetRow;
import com.github.git_leon.excel.ExcelSpreadSheet;
import com.github.git_leon.excel.ExcelSpreadSheetWorkBookFile;
import com.github.git_leon.utils.io.DirectoryReference;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;

import java.io.File;

/**
 * Created by leon on 2/6/2020.
 */
public class PrintAllData {
    @Test
    public void test() {
        File spreadSheetFile = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBookFile(spreadSheetFile);
        for (ExcelSpreadSheet spreadSheet : excelSpreadSheetWorkBook) {
            for (ExcelSpreadSheetRow row : spreadSheet.getRows()) {
                for (Cell cell : row) {
                    System.out.println(cell.toString());
                }
            }
        }
        excelSpreadSheetWorkBook.close();
    }
}
