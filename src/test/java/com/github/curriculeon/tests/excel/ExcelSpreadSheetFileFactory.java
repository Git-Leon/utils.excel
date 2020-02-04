package com.github.curriculeon.tests.excel;

import com.github.curriculeon.testingutils.TargetUtils;
import com.github.curriculeon.utils.file.BuildUtils;
import com.github.curriculeon.utils.file.directory.ResourceUtils;

import java.io.File;

public class ExcelSpreadSheetFileFactory {
    public static ExcelSpreadSheetWorkBookFile getMockData() {
        String fileName = "java-developer-philly-rubric-template";
        String fileExtension = ".xlsx";
        String sourceFilePath = fileName + fileExtension;
        File spreadSheetFile = BuildUtils.RESOURCEDIRECTORY.getFileFromDirectory(sourceFilePath);
        ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBookFile(spreadSheetFile);
        return excelSpreadSheetWorkBook.copyTo(new File(new StringBuilder()
                .append(TargetUtils.getTargetDirectoryFile())
                .append("/testoutput/")
                .append(fileName)
                .append("_")
                .append(System.nanoTime())
                .append(fileExtension)
                .toString()));
    }
}
