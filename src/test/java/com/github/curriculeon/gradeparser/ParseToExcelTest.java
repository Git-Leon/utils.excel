package com.github.curriculeon.gradeparser;

import com.github.curriculeon.engine.CSVSanitizer;
import com.github.curriculeon.engine.GradeParser;
import com.github.curriculeon.excel.ExcelSpreadSheetFile;
import com.github.curriculeon.utils.ResourceUtils;
import org.junit.Test;

import java.io.File;

public class ParseToExcelTest {
    @Test
    public void test() {
        File source = ResourceUtils.getResourceFile("grades.csv");
        File destination = ResourceUtils.getDuplicateFile(source.getName());
        CSVSanitizer csvSanitizer = new CSVSanitizer(source, destination);
        File spreadSheetFile = ResourceUtils.getResourceFile("java-developer-philly-rubric-template.xlsx");
        ExcelSpreadSheetFile excelSpreadSheetFile = new ExcelSpreadSheetFile(spreadSheetFile);
        GradeParser gradeParser = new GradeParser(excelSpreadSheetFile, csvSanitizer);
        gradeParser.parseToExcel();
    }
}
