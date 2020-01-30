package com.github.curriculeon.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;

public class ExcelSpreadSheetWorkBookCloner {
    Workbook oldWorkbook;

    public ExcelSpreadSheetWorkBookCloner(Workbook oldWorkbook) {
        this.oldWorkbook = oldWorkbook;
    }

    public Workbook copy(Workbook newWorkbook, String bookName) {
        try {
            return _copy(newWorkbook, bookName);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    private  Workbook _copy(Workbook newWorkbook, String bookName) throws IOException {
        // Need this to copy over styles from old sheet to new sheet. Next step will be processed below
        CellStyle newStyle = newWorkbook.createCellStyle();
        Row row;
        Cell cell;
        for (int i = 0; i < oldWorkbook.getNumberOfSheets(); i++) {
            HSSFSheet sheetFromOldWorkbook = (HSSFSheet) oldWorkbook.getSheetAt(i);
            HSSFSheet sheetForNewWorkbook = (HSSFSheet) newWorkbook.createSheet(bookName + "-" + sheetFromOldWorkbook.getSheetName());
            for (int rowIndex = 0; rowIndex < sheetFromOldWorkbook.getPhysicalNumberOfRows(); rowIndex++) {
                row = sheetForNewWorkbook.createRow(rowIndex); //create row in this new sheet
                for (int colIndex = 0; colIndex < sheetFromOldWorkbook.getRow(rowIndex).getPhysicalNumberOfCells(); colIndex++) {
                    cell = row.createCell(colIndex); //create cell in this row of this new sheet
                    //get cell from old/original Workbook's sheet and when cell is null, return it as blank cells.
                    //And Blank cell will be returned as Blank cells. That will not change.
                    Cell c = sheetFromOldWorkbook.getRow(rowIndex).getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    if (c.getCellTypeEnum() == CellType.BLANK) {
                        //System.out.println("This is BLANK " +  ((XSSFCell) c).getReference());
                    } else {
                        //Below is where all the copying is happening.
                        //First it copies the styles of each cell and then it copies the content.
                        CellStyle origStyle = c.getCellStyle();
                        newStyle.cloneStyleFrom(origStyle);
                        cell.setCellStyle(newStyle);

                        switch (c.getCellTypeEnum()) {
                            case STRING:
                                cell.setCellValue(c.getRichStringCellValue().getString());
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    cell.setCellValue(c.getDateCellValue());
                                } else {
                                    cell.setCellValue(c.getNumericCellValue());
                                }
                                break;
                            case BOOLEAN:
                                cell.setCellValue(c.getBooleanCellValue());
                                break;
                            case FORMULA:
                                cell.setCellValue(c.getCellFormula());
                                break;
                            case BLANK:
                                cell.setCellValue("");
                                break;
                            default:
                                System.out.println();
                        }
                    }
                }
            }

        }

        return newWorkbook;
    }
}
