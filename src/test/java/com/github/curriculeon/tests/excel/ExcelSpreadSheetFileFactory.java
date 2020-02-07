package com.github.curriculeon.tests.excel;

import com.github.curriculeon.utils.io.DirectoryReference;

import java.io.File;

public class ExcelSpreadSheetFileFactory {
    public static ExcelSpreadSheetWorkBookFile getMockData() {
        String fileName = "java-developer-philly-rubric-template";
        String fileExtension = ".xlsx";
        String sourceFilePath = fileName + fileExtension;
        File spreadSheetFile = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory(sourceFilePath);
        return new ExcelSpreadSheetWorkBookFile(spreadSheetFile);
    }
}
