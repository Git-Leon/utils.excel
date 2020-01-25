package com.github.curriculeon;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * @author leonhunter
 * @created 01/25/2020 - 1:59 AM
 */
public class ExcelSpreadSheetCellStyler implements ExcelSpreadSheetTableCollection {
    private ExcelSpreadSheet excelSpreadSheet;

    public ExcelSpreadSheetCellStyler(ExcelSpreadSheet excelSpreadSheet) {
        this.excelSpreadSheet = excelSpreadSheet;
    }


    public void setStyle(CellStyle var1) {

    }

    public Workbook getWorkBook() {
        return excelSpreadSheet.getWorkBook();
    }

    public void setCellComment(Comment var1) {

    }


    public void setHyperlink(Hyperlink var1) {

    }

    @Override
    public List<String> getData() {
        return null;
    }

}
