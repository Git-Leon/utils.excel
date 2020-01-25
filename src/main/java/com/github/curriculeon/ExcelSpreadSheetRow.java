package com.github.curriculeon;

import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:00 AM
 */
public class ExcelSpreadSheetRow implements ExcelSpreadSheetTableData{
    private List<String> data;

    public ExcelSpreadSheetRow(List<String> data) {
        this.data = data;
    }

    @Override
    public void setValue(double var1) {

    }

    @Override
    public void setValue(Date var1) {

    }

    @Override
    public void setValue(Calendar var1) {

    }

    @Override
    public void setValue(RichTextString var1) {

    }

    @Override
    public void setValue(String var1) {

    }

    @Override
    public void setCellFormula(String var1) throws FormulaParseException {

    }

    @Override
    public void setStyle(CellStyle var1) {

    }

    @Override
    public void setCellComment(Comment var1) {

    }

    @Override
    public void setHyperlink(Hyperlink var1) {

    }
}
