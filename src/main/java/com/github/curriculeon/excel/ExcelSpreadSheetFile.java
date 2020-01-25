package com.github.curriculeon.excel;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author leonhunter
 * @created 01/24/2020 - 10:35 PM
 */
public class ExcelSpreadSheetFile {
    private final Workbook workbook;
    private List<ExcelSpreadSheet> sheets;

    public ExcelSpreadSheetFile(File csvFile) {
        try {
            this.workbook = new XSSFWorkbook(new FileInputStream(csvFile));
            int counter = 0;
            ExcelSpreadSheet currentSheet = getExcelSpreadSheetAt(counter);
            do {
                sheets.add(getExcelSpreadSheetAt(counter));
            } while (currentSheet != null);
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public void setMacro(ExcelMacro excelMacro) {

    }

    public ExcelSpreadSheet getExcelSpreadSheetAt(Integer index) {
        return new ExcelSpreadSheet(workbook.getSheetAt(index));
    }


    public ExcelSpreadSheet getExcelSpreadSheet(String name) {
        return new ExcelSpreadSheet(workbook.getSheet(name));
    }

}
