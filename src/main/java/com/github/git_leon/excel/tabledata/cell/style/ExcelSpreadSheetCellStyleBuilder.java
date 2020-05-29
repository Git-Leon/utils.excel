package com.github.git_leon.excel.tabledata.cell.style;

import org.apache.poi.ss.usermodel.*;

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
    private HorizontalAlignment horizontalAlignment;
    private VerticalAlignment verticalAlignment;
    private Short rotation;
    private Short indentation;
    private BorderStyle borderLeft;
    private BorderStyle  borderRight;
    private BorderStyle borderTop;
    private BorderStyle borderBottom;
    private Short  borderColorLeft;
    private Short borderColorRight;
    private Short borderColorTop;
    private Short borderColorBottom;
    private FillPatternType fillPattern;
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

    public ExcelSpreadSheetCellStyleBuilder setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setVerticalAlignment(VerticalAlignment verticalAlignment) {
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

    public ExcelSpreadSheetCellStyleBuilder setBorderLeft(BorderStyle borderLeft) {
        this.borderLeft = borderLeft;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setBorderRight(BorderStyle borderRight) {
        this.borderRight = borderRight;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setBorderTop(BorderStyle borderTop) {
        this.borderTop = borderTop;
        return this;
    }

    public ExcelSpreadSheetCellStyleBuilder setBorderBottom(BorderStyle borderBottom) {
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

    public ExcelSpreadSheetCellStyleBuilder setFillPattern(FillPatternType fillPattern) {
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
        cellStyle.setAlignment(horizontalAlignment);
        cellStyle.setWrapText(isTextWrapped);
        cellStyle.setVerticalAlignment(verticalAlignment);
        cellStyle.setRotation(rotation);
        cellStyle.setIndention(indentation);
        cellStyle.setBorderLeft(borderLeft);
        cellStyle.setBorderRight(borderRight);
        cellStyle.setBorderTop(borderTop);
        cellStyle.setBorderBottom(borderBottom);
        cellStyle.setLeftBorderColor(borderColorLeft);
        cellStyle.setRightBorderColor(borderColorRight);
        cellStyle.setTopBorderColor(borderColorTop);
        cellStyle.setBottomBorderColor(borderColorBottom);
        cellStyle.setFillPattern(fillPattern);
        cellStyle.setFillBackgroundColor(fillBackgroundColor);
        cellStyle.setFillForegroundColor(fillForegroundColor);
        return new ExcelSpreadSheetCellStyle(cellStyle);
    }
}
