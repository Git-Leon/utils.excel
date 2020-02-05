package com.github.curriculeon.tests.gradeparser;

import com.github.curriculeon.engine.CSVSanitizer;
import com.github.curriculeon.engine.GradeParser;
import com.github.curriculeon.tests.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.utils.io.DirectoryReference;
import com.github.curriculeon.utils.io.FileWrapper;
import org.junit.Test;

import java.io.File;

public class ParseToExcelTest {
    DirectoryReference resourceDir = DirectoryReference.RESOURCEDIRECTORY;
    DirectoryReference targetDir = DirectoryReference.TARGETDIRECTORY;

    private FileWrapper getCsvSource() {
        String sourceCsvFileName = "grades";
        String sourceCsvFileExtension = ".csv";
        String sourceCsvFileNameAndExtension = sourceCsvFileName + sourceCsvFileExtension;
        File csvSource = resourceDir.getFileFromDirectory(sourceCsvFileNameAndExtension);
        return new FileWrapper(csvSource);
    }

    private File getCsvDestination() {
        FileWrapper src =  getCsvSource();
        return getCsvSource().copyTo(new StringBuilder()
                .append(targetDir.getDirectoryPath())
                .append("SANITIZED-")
                .append(src.getName())
                .append(Long.valueOf(String.valueOf(System.nanoTime()), 16)) // salt
                .append(src.getFileExtension())
                .toString());
    }

    @Test
    public void test() {
        FileWrapper csvSource = getCsvSource();
        File csvDestination = getCsvDestination();
        CSVSanitizer csvSanitizer = new CSVSanitizer(csvSource.getFile(), csvDestination);
        File spreadSheetFileSource = resourceDir.getFileFromDirectory("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetWorkBookFile excelSource = new ExcelSpreadSheetWorkBookFile(spreadSheetFileSource);
        GradeParser gradeParser = new GradeParser(excelSource, csvSanitizer);
        gradeParser.parseToExcel();
        ExcelSpreadSheetWorkBookFile excelDestination = gradeParser.getExcelSpreadSheetWorkBookDestination();
    }
}
