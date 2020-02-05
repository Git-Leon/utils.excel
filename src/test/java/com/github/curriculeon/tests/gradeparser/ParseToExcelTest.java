package com.github.curriculeon.tests.gradeparser;

import com.github.curriculeon.engine.CSVSanitizer;
import com.github.curriculeon.engine.GradeParser;
import com.github.curriculeon.testingutils.TargetUtils;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.utils.file.DirectoryReference;
import com.github.curriculeon.utils.file.FileWrapper;
import org.junit.Test;

import java.io.File;

public class ParseToExcelTest {
    @Test
    public void test() {
        String fileName = "grades";
        String fileExtension = ".csv";
        String fileNameAndExtension = fileName + fileExtension;
        File source = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory(fileNameAndExtension);
        File destination = DirectoryReference.TARGETDIRECTORY
                .getFileFromDirectory(new StringBuilder()
                        .append("/testoutput/")
                        .append(fileName)
                        .append("_")
                        .append(System.nanoTime())
                        .append(fileExtension)
                        .toString());
        CSVSanitizer csvSanitizer = new CSVSanitizer(source, destination);
        File spreadSheetFile = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBookFile excelSpreadSheetWorkBook = new ExcelSpreadSheetWorkBookFile(spreadSheetFile);
        GradeParser gradeParser = new GradeParser(excelSpreadSheetWorkBook, csvSanitizer);
        gradeParser.parseToExcel();
    }
}
