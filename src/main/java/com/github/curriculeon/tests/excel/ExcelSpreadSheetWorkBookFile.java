package com.github.curriculeon.tests.excel;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author leonhunter
 * @created 01/31/2020 - 5:32 PM
 */
public class ExcelSpreadSheetWorkBookFile implements ExcelSpreadSheetWorkBookFileInterface {
    private final Workbook workbook;
    private final File file;

    public ExcelSpreadSheetWorkBookFile(File file) {
        try {
            this.file = file;
            this.workbook = WorkbookFactory.create(file);;
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public ExcelSpreadSheetWorkBookFile(String filePath) {
        this(new File(filePath));
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public Workbook getWorkBook() {
        return workbook;
    }

    @Override
    public void finalize() {
        close();
    }
}
