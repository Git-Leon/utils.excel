package com.github.curriculeon.excel;

import com.github.curriculeon.excel.tabledata.metadata.ExcelMacro;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author leonhunter
 * @created 01/24/2020 - 10:35 PM
 */
public class ExcelSpreadSheetWorkBook implements Iterable<ExcelSpreadSheet> {
    private final Workbook workbook;
    private final File file;

    public ExcelSpreadSheetWorkBook(String filePath) {
        this(new File(filePath));
    }

    public ExcelSpreadSheetWorkBook(File file) {
        try {
            this.file = file;
            this.workbook = new XSSFWorkbook(new FileInputStream(file));
            this.addSheets(getSheetsFromWorkBook());
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public File getFile() {
        return file;
    }

    public List<ExcelSpreadSheet> getExcelSpreadSheets() {
        return getSheetsFromWorkBook()
                .stream()
                .map(sheet -> new ExcelSpreadSheet(sheet))
                .collect(Collectors.toList());
    }

    public List<Sheet> getSheetsFromWorkBook() {
        List<Sheet> list = new ArrayList<>();
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            list.add(sheet);
        }
        return list;
    }

    public ExcelSpreadSheetWorkBook copyTo(File destination) {
        try {
            FileUtils.copyFile(file, destination);
            return new ExcelSpreadSheetWorkBook(destination);
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public void setMacro(ExcelMacro excelMacro) {

    }

    public void addSheet(Sheet sheet) {
        new ExcelSpreadSheetCloner(sheet).clone(workbook, file.getName());
    }


    public void addSheet(Sheet sheet, String newName) {
        new ExcelSpreadSheetCloner(sheet).clone(workbook, file.getName(), newName);
    }

    public void addSheets(Iterable<Sheet> sheets) {
        sheets.forEach(this::addSheet);
    }

    public ExcelSpreadSheet getExcelSpreadSheetByName(String sheetName) {
        return getSheetsFromWorkBook()
                .stream()
                .filter(sheet -> sheet.getSheetName().equals(sheetName))
                .findFirst()
                .or(() -> Optional.of(workbook.createSheet(sheetName)))
                .map(ExcelSpreadSheet::new)
                .get();

    }

    public Optional<ExcelSpreadSheet> getExcelSpreadSheetByIndex(Integer index) {
        try {
            return Optional.of(new ExcelSpreadSheet(workbook.getSheetAt(index)));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }


    private ExcelSpreadSheet createNewExcelSpreadSheet(String name) {
        return new ExcelSpreadSheet(workbook.createSheet(name));
    }

    @Override
    public Iterator<ExcelSpreadSheet> iterator() {
        return this.getExcelSpreadSheets().iterator();
    }

    @Override
    public String toString() {
        return "ExcelSpreadSheetWorkBook{" +
                "workbook=" + workbook +
                ", file=" + file +
                ", sheets=" + this.getExcelSpreadSheets().toString().replace(", ", "\n") +
                '}';
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

    public void setSheetOrder(String sheetName, Integer newSheetIndex) {
        workbook.setSheetOrder(sheetName, newSheetIndex);
    }

    public void setActive(Sheet newSheet) {
        workbook.setActiveSheet(workbook.getSheetIndex(newSheet.getSheetName()));
    }

    public Boolean containsSheet(Sheet sheet) {
        return getSheetsFromWorkBook().contains(sheet);
    }

    public Boolean containsSheet(String name) {
        return getSheetsFromWorkBook()
                .stream()
                .anyMatch(excelSheet -> excelSheet.getSheetName().equals(name));
    }
}
