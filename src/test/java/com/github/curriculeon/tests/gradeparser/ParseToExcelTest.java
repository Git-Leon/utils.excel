package com.github.curriculeon.tests.gradeparser;

import com.github.curriculeon.engine.CSVSanitizer;
import com.github.curriculeon.engine.GradeParser;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetFileFactory;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.utils.io.DirectoryReference;
import org.junit.Test;

import java.io.File;

public class ParseToExcelTest {
    @Test
    public void test() {
        String fileName = "grades";
        String fileExtension = ".csv";
        String fileNameAndExtension = fileName + fileExtension;
        File csvSource = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory(fileNameAndExtension);
        File csvDestination = DirectoryReference.TARGETDIRECTORY
                .copyFile(csvSource.getName());
        CSVSanitizer csvSanitizer = new CSVSanitizer(csvSource, csvDestination);
        File spreadSheetFileSource = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBookFile excelSource = new ExcelSpreadSheetWorkBookFile(spreadSheetFileSource);
        GradeParser gradeParser = new GradeParser(excelSource, csvSanitizer);
        gradeParser.parseToExcel();
        ExcelSpreadSheetWorkBookFile excelDestination = gradeParser.getExcelSpreadSheetWorkBookDestination();
    }
}
