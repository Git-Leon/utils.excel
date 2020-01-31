package com.github.curriculeon.excel;

import com.github.curriculeon.utils.ResourceUtils;

import java.io.File;

public class ExcelSpreadSheetFileFactory {
    public static DeprecatedExcelSpreadSheetWorkBook getMockData() {
        String fileName = "java-developer-philly-rubric-template";
        String fileExtension = ".xlsx";
        String sourceFilePath = fileName + fileExtension;
        File spreadSheetFile = ResourceUtils.getResourceFile(sourceFilePath);
        DeprecatedExcelSpreadSheetWorkBook excelSpreadSheetWorkBook = new DeprecatedExcelSpreadSheetWorkBook(spreadSheetFile);
        return excelSpreadSheetWorkBook.copyTo(ResourceUtils.copyFile(sourceFilePath));
    }
}
