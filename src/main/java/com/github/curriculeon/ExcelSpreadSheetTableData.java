package com.github.curriculeon;

import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Calendar;
import java.util.Date;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:05 AM
 */
public interface ExcelSpreadSheetTableData {
    void setValue(double var1);

    void setValue(Date var1);

    void setValue(Calendar var1);

    void setValue(RichTextString var1);

    void setValue(String var1);

    void setCellFormula(String var1) throws FormulaParseException;

    void setStyle(CellStyle var1);

    void setCellComment(Comment var1);

    void setHyperlink(Hyperlink var1);
}
