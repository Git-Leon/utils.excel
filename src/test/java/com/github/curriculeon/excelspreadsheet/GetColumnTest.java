package com.github.curriculeon.excelspreadsheet;

import com.github.curriculeon.ExcelSpreadSheet;
import com.github.curriculeon.ExcelSpreadSheetFile;
import com.github.curriculeon.ExcelSpreadSheetRow;
import com.github.curriculeon.utils.ResourceUtils;
import org.junit.Test;

import java.io.File;

public class GetColumnTest {
    @Test
    public void test() {
        File csv = ResourceUtils.duplicateFile("grades.csv");
        ExcelSpreadSheetFile spreadSheetFile = new ExcelSpreadSheetFile(csv);
        ExcelSpreadSheet spreadSheet = spreadSheetFile.getExcelSpreadSheetAt(0);
        ExcelSpreadSheetRow row = spreadSheet.getRow(0);
        String output = row.toString();
        System.out.println(output);
    }
}
