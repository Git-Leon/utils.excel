package com.github.curriculeon.tests.excel;

import com.github.curriculeon.utils.io.DirectoryReference;

import java.io.File;

public class ExcelSpreadSheetFileFactory {
    public static ExcelSpreadSheetWorkBookFile getMockData() {
        String fileName = "java-developer-philly-rubric-template";
        String fileExtension = ".xlsx";
        String sourceFilePath = fileName + fileExtension;
        File spreadSheetFile = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory(sourceFilePath);
        ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBookFile(spreadSheetFile);
        return excelSpreadSheetWorkBook.copyTo(DirectoryReference.TARGETDIRECTORY
                .getFileFromDirectory(new StringBuilder()
                        .append("input-copy")
                        .append("COPYOF-")
                        .append(fileName)
                        .append("_")
                        .append(Long.toHexString(System.nanoTime()))
                        .append(fileExtension)
                        .toString()));
    }
}
