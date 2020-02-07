package com.github.curriculeon.tests.excel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;

public interface ExcelSpreadSheetWorkBookFileInterface extends Closeable, ExcelSpreadSheetWorkBookInterface {
    File getFile();

    Workbook getWorkBook();


    default void finalize() {
        close();
    }

    /**
     * without closing the input stream, we leave ourselves vulnerable to `EmptyFileException`
     * read more about the issue by visiting the link below
     * https://stackoverflow.com/questions/34264120/java-apache-poi-read-write-xlsx-file-file-getting-corrupted-and-becomes-e/34264649
     */
    default void close() {
        try {
            IOUtils.closeQuietly(new FileInputStream(getFile()));
        } catch (FileNotFoundException e) {
            throw new Error(e);
        }
    }


    default void addSheets(Iterable<Sheet> sheets) {
        sheets.forEach(this::addSheet);
    }


    default void addSheet(Sheet sheet, String newName) {
        new ExcelSpreadSheetCloner(sheet).clone(getWorkBook(), getFile().getName(), newName);
    }


    default void addSheet(Sheet sheet) {
        addSheet(sheet, getFile().getName());
    }


    default ExcelSpreadSheetWorkBookFile copyTo(File destination) {
        try {
            FileUtils.copyFile(getFile(), destination);
            return new ExcelSpreadSheetWorkBookFile(destination);
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    default void flush() {
        try {
            FileOutputStream out = new FileOutputStream(getFile());
            getWorkBook().write(out);
            out.close();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

}
