package com.github.curriculeon.tests.excel;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author leonhunter
 * @created 01/31/2020 - 5:32 PM
 */
public class ExcelSpreadSheetWorkBookFile implements ExcelSpreadSheetWorkBookInterface {
    private Workbook workbook;
    private File file;

    public ExcelSpreadSheetWorkBookFile(Workbook workbook, File file) {
        this.workbook = workbook;
        this.file = file;
    }

    public ExcelSpreadSheetWorkBookFile(File file) {
        try {
            this.file = file;
            this.workbook = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public ExcelSpreadSheetWorkBookFile(String filePath) {
        this(new File(filePath));
    }


    public Workbook getWorkbook() {
        return workbook;
    }

    public File getFile() {
        return file;
    }


    public ExcelSpreadSheetWorkBookFile copyTo(File destination) {
        try {
            FileUtils.copyFile(file, destination);
            return new ExcelSpreadSheetWorkBookFile(destination);
        } catch (IOException e) {
            throw new Error(e);
        }
    }
    public void addSheets(Iterable<Sheet> sheets) {
        sheets.forEach(this::addSheet);
    }

    public void addSheet(Sheet sheet, String newName) {
        new ExcelSpreadSheetCloner(sheet).clone(workbook, file.getName(), newName);
    }

    public void addSheet(Sheet sheet) {
        addSheet(sheet, file.getName());
    }

    public void deleteSheet(int i) {
        workbook.removeSheetAt(i);
    }

    public void deleteSheet(String sheetName) {
        for (ExcelSpreadSheet spreadSheet : this) {
            if (spreadSheet.getName().equals(sheetName)) {
                workbook.removeSheetAt(spreadSheet.getSheetIndex());
            }
        }
    }

    public void deleteSheetsAfter(int finalIndex) {
        for (ExcelSpreadSheet sheet : this) {
            if (sheet.getSheetIndex() > finalIndex) {
                workbook.removeSheetAt(finalIndex);
            }
        }
    }

    public void flush() {
        try {
            FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    @Override
    public void finalize() {
        this.flush();
    }

    @Override
    public Workbook getWorkBook() {
        return workbook;
    }
}
