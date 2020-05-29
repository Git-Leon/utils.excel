package com.github.git_leon.excel;

import com.github.git_leon.utils.io.DirectoryReference;

import java.io.File;
import java.io.IOException;

public class ExcelSpreadSheetFileFactory {
    public static ExcelSpreadSheetWorkBookFile getCopyOfRubricTemplateWorkBookFile() {
        String fileName = "java-developer-philly-rubric-template";
        String fileExtension = ".xlsx";
        String sourceFilePath = fileName + fileExtension;
        File spreadSheetFile = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory(sourceFilePath);
        ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBookFile(spreadSheetFile);
        return excelSpreadSheetWorkBook.copyTo(DirectoryReference.TARGETDIRECTORY
                .getFileFromDirectory(new StringBuilder()
                        .append("mockinput/")
                        .append("COPYOF-")
                        .append(fileName)
                        .append("_")
                        .append(Long.toHexString(System.nanoTime()))
                        .append(fileExtension)
                        .toString()));
    }

    public static ExcelSpreadSheetWorkBookFile getNewExcelWorkBookFile() {
        // given
        File file = DirectoryReference.TARGETDIRECTORY.getFileFromDirectory(new StringBuilder()
                .append("test")
                .append(System.nanoTime())
                .append(".xlsx")
                .toString());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new Error(e);
            }
        }
        System.err.printf("Creating workbook [ %s ]\n", file.getName());
        ExcelSpreadSheetWorkBookFile workBookFile = new ExcelSpreadSheetWorkBookFile(file);
        return workBookFile;
    }
}
