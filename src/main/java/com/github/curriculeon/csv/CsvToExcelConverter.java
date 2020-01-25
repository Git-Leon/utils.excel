package com.github.curriculeon.csv;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class CsvToExcelConverter {
    private File csvFile;
    private File xlsxFile;

    public CsvToExcelConverter(File csvFile, File xlsxFile) {
        this.csvFile = csvFile;
        this.xlsxFile = xlsxFile;
    }

    public CsvToExcelConverter(String csvFilePath, String xlsxFilePath) {
        this(new File(csvFilePath), new File(xlsxFilePath));
    }

    public void csvToXLSX() {
        try {
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("sheet1");
            String currentLine = null;
            int rowNumber = 0;
            BufferedReader br = new BufferedReader(new FileReader(csvFile.getAbsolutePath()));
            for (String cellArray[] = currentLine.split(","); br.readLine() != null; rowNumber++) {
                XSSFRow currentRow = sheet.createRow(rowNumber);
                for (int i = 0; i < cellArray.length; i++) {
                    currentRow.createCell(i).setCellValue(cellArray[i]);
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(xlsxFile.getAbsolutePath());
            workBook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
