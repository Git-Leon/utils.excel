package com.github.curriculeon.tests.excel.tabledata.cell;

import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author leonhunter
 * @created 02/09/2020 - 11:06 PM
 */
public interface ExcelSpreadSheetCellDecoratorInterface extends Cell {
    Cell getCell();

    @Override
    default int getColumnIndex() {
        return getCell().getColumnIndex();
    }


    @Override
    default int getRowIndex() {
        return getCell().getRowIndex();
    }


    @Override
    default Sheet getSheet() {
        return getCell().getSheet();
    }


    @Override
    default Row getRow() {
        return getCell().getRow();
    }


    @Override
    default void setCellType(CellType cellType) {
        getCell().setCellType(cellType);
    }


    @Override
    default void setBlank() {
        getCell().setBlank();
    }


    @Override
    default CellType getCellType() {
        return getCell().getCellType();
    }


    @Override
    default CellType getCellTypeEnum() {
        return getCell().getCellType();
    }


    @Override
    default CellType getCachedFormulaResultType() {
        return getCell().getCachedFormulaResultType();
    }


    @Override
    default CellType getCachedFormulaResultTypeEnum() {
        return getCell().getCachedFormulaResultType();
    }


    @Override
    default void setCellValue(double v) {
        getCell().setCellValue(v);
    }


    @Override
    default void setCellValue(Date date) {
        getCell().setCellValue(date);
    }


    @Override
    default void setCellValue(LocalDateTime localDateTime) {
        getCell().setCellValue(localDateTime);
    }


    @Override
    default void setCellValue(Calendar calendar) {
        getCell().setCellValue(calendar);
    }


    @Override
    default void setCellValue(RichTextString richTextString) {
        getCell().setCellValue(richTextString);
    }


    @Override
    default void setCellValue(String s) {
        getCell().setCellValue(s);
    }


    @Override
    default void setCellFormula(String s) throws FormulaParseException, IllegalStateException {
        getCell().setCellFormula(s);
    }


    @Override
    default void removeFormula() throws IllegalStateException {
        getCell().removeFormula();
    }


    @Override
    default String getCellFormula() {
        return getCell().getCellFormula();
    }


    @Override
    default double getNumericCellValue() {
        return getCell().getNumericCellValue();
    }


    @Override
    default Date getDateCellValue() {
        return getCell().getDateCellValue();
    }


    @Override
    default LocalDateTime getLocalDateTimeCellValue() {
        return getCell().getLocalDateTimeCellValue();
    }


    @Override
    default RichTextString getRichStringCellValue() {
        return getCell().getRichStringCellValue();
    }


    @Override
    default String getStringCellValue() {
        return getCell().getStringCellValue();
    }


    @Override
    default void setCellValue(boolean b) {
        getCell().setCellValue(b);
    }


    @Override
    default void setCellErrorValue(byte b) {
        getCell().setCellErrorValue(b);
    }


    @Override
    default boolean getBooleanCellValue() {
        return getCell().getBooleanCellValue();
    }


    @Override
    default byte getErrorCellValue() {
        return getCell().getErrorCellValue();
    }


    @Override
    default void setCellStyle(CellStyle cellStyle) {
        getCell().setCellStyle(cellStyle);
    }


    @Override
    default CellStyle getCellStyle() {
        return getCell().getCellStyle();
    }


    @Override
    default void setAsActiveCell() {
        getCell().setAsActiveCell();
    }


    @Override
    default CellAddress getAddress() {
        return getCell().getAddress();
    }


    @Override
    default void setCellComment(Comment comment) {
        getCell().setCellComment(comment);
    }


    @Override
    default Comment getCellComment() {
        return getCell().getCellComment();
    }


    @Override
    default void removeCellComment() {
        getCell().removeCellComment();
    }


    @Override
    default Hyperlink getHyperlink() {
        return getCell().getHyperlink();
    }


    @Override
    default void setHyperlink(Hyperlink hyperlink) {
        getCell().setHyperlink(hyperlink);
    }


    @Override
    default void removeHyperlink() {
        getCell().removeHyperlink();
    }


    @Override
    default CellRangeAddress getArrayFormulaRange() {
        return getCell().getArrayFormulaRange();
    }


    @Override
    default boolean isPartOfArrayFormulaGroup() {
        return getCell().isPartOfArrayFormulaGroup();
    }
}
