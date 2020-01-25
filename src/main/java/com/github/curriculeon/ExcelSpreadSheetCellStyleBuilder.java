package com.github.curriculeon;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 * @author leonhunter
 * @created 01/25/2020 - 2:40 AM
 */
public class ExcelSpreadSheetCellStyleBuilder {
    private final CellStyle cellStyle;
    private Short dataFormat;
    private Font font;
    private Boolean isHidden;
    private Boolean isLocked;
    private Boolean isTextWrapped;
    private Short alignment;
    private Short verticalAlignment;
    private Short rotation;
    private Short indentation;
    private Short borderLeft;
    private Short borderRight;
    private Short borderTop;
    private Short borderBottom;
    private Short borderColorLeft;
    private Short borderColorRight;
    private Short borderColorTop;
    private Short borderColorBottom;
    private Short fillPattern;
    private Short fillBackgroundColor;
    private Short fillForegroundColor;

    ExcelSpreadSheetCellStyleBuilder(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public ExcelSpreadSheetCellStyleBuilder setDataFormat(Short dataFormat) {
        this.dataFormat = dataFormat;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setFont(Font font) {
        this.font = font;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setIsTextWrapped(Boolean isTextWrapped) {
        this.isTextWrapped = isTextWrapped;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setAlignment(Short alignment) {
        this.alignment = alignment;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setVerticalAlignment(Short verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setRotation(Short rotation) {
        this.rotation = rotation;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setIndentation(Short indentation) {
        this.indentation = indentation;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setBorderLeft(Short borderLeft) {
        this.borderLeft = borderLeft;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setBorderRight(Short borderRight) {
        this.borderRight = borderRight;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setBorderTop(Short borderTop) {
        this.borderTop = borderTop;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setBorderBottom(Short borderBottom) {
        this.borderBottom = borderBottom;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setBorderColorLeft(Short borderColorLeft) {
        this.borderColorLeft = borderColorLeft;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setBorderColorRight(Short borderColorRight) {
        this.borderColorRight = borderColorRight;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setBorderColorTop(Short borderColorTop) {
        this.borderColorTop = borderColorTop;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setBorderColorBottom(Short borderColorBottom) {
        this.borderColorBottom = borderColorBottom;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setFillPattern(Short fillPattern) {
        this.fillPattern = fillPattern;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setFillBackgroundColor(Short fillBackgroundColor) {
        this.fillBackgroundColor = fillBackgroundColor;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setFillForegroundColor(Short fillForegroundColor) {
        this.fillForegroundColor = fillForegroundColor;
        return this;
    }

    public ExcelSpreadSheetCellStyle build() {
        cellStyle.setDataFormat(dataFormat);
        cellStyle.setFont(font);
        cellStyle.setHidden(isHidden);
        cellStyle.setLocked(isLocked);
        cellStyle.setAlignment(alignment);
        cellStyle.setWrapText(isTextWrapped);
        cellStyle.setVerticalAlignment(verticalAlignment);
        cellStyle.setRotation(rotation);
        cellStyle.setIndention(indentation);
        cellStyle.setBorderLeft(borderLeft);
        cellStyle.setBorderRight(borderRight);
        cellStyle.setBorderTop(borderTop);
        cellStyle.setBorderBottom(borderBottom);
        cellStyle.setLeftBorderColor(borderLeft);
        cellStyle.setRightBorderColor(borderRight);
        cellStyle.setTopBorderColor(borderTop);
        cellStyle.setBottomBorderColor(borderBottom);
        cellStyle.setFillPattern(fillPattern);
        cellStyle.setFillBackgroundColor(fillBackgroundColor);
        cellStyle.setFillForegroundColor(fillForegroundColor);
        return new ExcelSpreadSheetCellStyle(cellStyle);
    }
}
