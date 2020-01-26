package com.github.curriculeon.excel;

import com.github.curriculeon.excel.tabledata.metadata.ExcelMacro;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author leonhunter
 * @created 01/24/2020 - 10:35 PM
 */
public class ExcelSpreadSheetFile implements Iterable<ExcelSpreadSheet>{
    private final Workbook workbook;
    private final File file;
    private List<ExcelSpreadSheet> sheets;

    public ExcelSpreadSheetFile(String filePath) {
        this(new File(filePath));
    }

    public ExcelSpreadSheetFile(File file) {
        this.file = file;
        this.sheets = new ArrayList<>();
        try {
            this.workbook = new XSSFWorkbook(new FileInputStream(file));
            int counter = 0;
            ExcelSpreadSheet currentSheet;
            do {
                currentSheet = getExcelSpreadSheetAt(counter).orElse(null);
                if(currentSheet!= null) {
                    sheets.add(currentSheet);
                    counter++;
                }
            } while (currentSheet != null);
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public ExcelSpreadSheetFile copyTo(File destination) {
        try {
            FileUtils.copyFile(file, destination);
            return new ExcelSpreadSheetFile(destination);
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public void setMacro(ExcelMacro excelMacro) {

    }

    public Optional<ExcelSpreadSheet> getExcelSpreadSheetAt(Integer index) {
        try {
            return Optional.of(new ExcelSpreadSheet(workbook.getSheetAt(index)));
        } catch(IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public ExcelSpreadSheet getOrCreateSpreadSheetAt(Integer index) {
        Sheet potentialNewSheet = workbook.createSheet("New Sheet" + System.nanoTime());
        ExcelSpreadSheet excelSpreadSheet = new ExcelSpreadSheet(potentialNewSheet);
        return getExcelSpreadSheetAt(index).orElse(excelSpreadSheet);
    }

    public ExcelSpreadSheet getExcelSpreadSheet(String name) {
        return new ExcelSpreadSheet(workbook.getSheet(name));
    }

    public ExcelSpreadSheet getNewSpreadSheet(String sheetName) {
        return new ExcelSpreadSheet(workbook.createSheet(sheetName));
    }

    @Override
    public Iterator<ExcelSpreadSheet> iterator() {
        return this.sheets.iterator();
    }
}
