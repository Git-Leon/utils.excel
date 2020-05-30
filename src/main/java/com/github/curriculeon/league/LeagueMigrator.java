package com.github.curriculeon.league;

import com.github.curriculeon.excel.ExcelSpreadSheet;
import com.github.curriculeon.excel.ExcelSpreadSheetWorkBookFile;
import com.github.curriculeon.excel.tabledata.cell.ExcelSpreadSheetCell;
import com.github.curriculeon.excel.tabledata.dataarray.ExcelSpreadSheetRow;
import com.github.curriculeon.utils.io.DirectoryReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leonhunter
 * @created 05/29/2020 - 8:29 PM
 */
public class LeagueMigrator {
    private final String excelSourceFileName;

    public LeagueMigrator(String excelSourceFileName) {
        this.excelSourceFileName = excelSourceFileName;
    }

    public String getSqlQuery() {
        List<League> leagues = extractAllLeaguesFromFile();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(League.getCreationStatement());
        leagues.forEach(league -> stringBuilder
            .append("\n")
            .append(league.getInsertionStatement()));
        return stringBuilder.toString();
    }

    private List<League> extractAllLeaguesFromFile() {
        List<League> result = new ArrayList<>();
        File source = DirectoryReference.RESOURCEDIRECTORY.getFileFromDirectory(excelSourceFileName);
        ExcelSpreadSheetWorkBookFile sourceWorkBookFile = new ExcelSpreadSheetWorkBookFile(source);
        ExcelSpreadSheet firstSheet = sourceWorkBookFile.getExcelSpreadSheetByIndex(0).get();
        for (ExcelSpreadSheetRow row : firstSheet.getRows()) {
            ExcelSpreadSheetCell countryName = row.getCell(0);
            ExcelSpreadSheetCell leagueName = row.getCell(1);
            League league = new League(countryName.getCellValue(), leagueName.getCellValue(), System.nanoTime());
            result.add(league);
        }
        return result;
    }
}
