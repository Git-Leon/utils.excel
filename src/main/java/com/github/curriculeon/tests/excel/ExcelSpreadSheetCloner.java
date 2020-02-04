package com.github.curriculeon.tests.excel;

import org.apache.poi.ss.usermodel.*;

import java.util.UUID;

public class ExcelSpreadSheetCloner {
    private Sheet sheet;

    public ExcelSpreadSheetCloner(Sheet sheetClonee) {
        this.sheet = sheetClonee;
    }

    public void clone(Workbook newWorkbook, String bookName) {
        clone(newWorkbook, bookName, UUID.randomUUID().toString());
    }

    public void clone(Workbook newWorkbook, String bookName, String sheetName) {
        CellStyle newStyle = newWorkbook.createCellStyle();
        Row row;
        Cell cell;
        Sheet sheetForNewWorkbook = newWorkbook.createSheet(bookName + sheetName);
        for (int rowIndex = 0; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
            row = sheetForNewWorkbook.createRow(rowIndex); //create row in this new sheet
            for (int colIndex = 0; colIndex < sheet.getRow(rowIndex).getPhysicalNumberOfCells(); colIndex++) {
                cell = row.createCell(colIndex); //create cell in this row of this new sheet
                //get cell from old/original Workbook's sheet and when cell is null, return it as blank cells.
                //And Blank cell will be returned as Blank cells. That will not change.
                Cell c = sheet.getRow(rowIndex).getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

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
}
