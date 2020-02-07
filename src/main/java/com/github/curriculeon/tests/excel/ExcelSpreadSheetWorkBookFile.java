package com.github.curriculeon.tests.excel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * @author leonhunter
 * @created 01/31/2020 - 5:32 PM
 */
public class ExcelSpreadSheetWorkBookFile implements Closeable, ExcelSpreadSheetWorkBookInterface {
    private final Workbook workbook;
    private final File file;

    public ExcelSpreadSheetWorkBookFile(File file) {
        try {
            this.file = file;
            FileInputStream inputStream = new FileInputStream(file);
            this.workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public ExcelSpreadSheetWorkBookFile(String filePath) {
        this(new File(filePath));
    }

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

    /**
     * without closing the input stream, we leave ourselves vulnerable to `EmptyFileException`
     * read more about the issue by visiting the link below
     * https://stackoverflow.com/questions/34264120/java-apache-poi-read-write-xlsx-file-file-getting-corrupted-and-becomes-e/34264649
     */
    @Override
    public void close() {
        try {
            IOUtils.closeQuietly(new FileInputStream(getFile()));
        } catch (FileNotFoundException e) {
            throw new Error(e);
        }
    }


    public void addSheets(Iterable<Sheet> sheets) {
        sheets.forEach(this::addSheet);
    }

    public void addSheet(Sheet sheet, String newName) {
        new ExcelSpreadSheetCloner(sheet).clone(getWorkBook(), getFile().getName(), newName);
    }

    public void addSheet(Sheet sheet) {
        addSheet(sheet, getFile().getName());
    }

    public void deleteSheet(int i) {
        getWorkBook().removeSheetAt(i);
    }

    public ExcelSpreadSheetWorkBookFile copyTo(File destination) {
        try {
            FileUtils.copyFile(getFile(), destination);
            return new ExcelSpreadSheetWorkBookFile(destination);
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public void deleteSheet(String sheetName) {
        for (ExcelSpreadSheet spreadSheet : this) {
            if (spreadSheet.getName().equals(sheetName)) {
                getWorkBook().removeSheetAt(spreadSheet.getSheetIndex());
            }
        }
    }

    public void deleteSheetsAfter(int finalIndex) {
        for (ExcelSpreadSheet sheet : this) {
            if (sheet.getSheetIndex() > finalIndex) {
                getWorkBook().removeSheetAt(finalIndex);
            }
        }
    }

    public void flush() {
        try {
            FileOutputStream out = new FileOutputStream(getFile());
            getWorkBook().write(out);
            out.close();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

}
