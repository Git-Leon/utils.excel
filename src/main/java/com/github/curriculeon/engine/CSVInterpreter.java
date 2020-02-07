package com.github.curriculeon.engine;

import com.github.curriculeon.utils.Transposer;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/07/2020 - 1:24 PM
 */
public class CSVInterpreter {
    private final List<List<String>> rows;

    public CSVInterpreter(File source) {
        try {
            CSVReader reader = new CSVReader(new FileReader(source.getAbsolutePath()));
            this.rows = Transposer.normalize(reader.readAll());
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public List<String> getRow(Integer columnNumber) {
        return getRows().get(columnNumber);
    }

    public List<String> getColumn(Integer columnNumber) {
        return new Transposer<>(getRows()).transpose().get(columnNumber);
    }

    public List<List<String>> getColumns() {
        List<List<String>> result = new ArrayList<>();
        for(List<String> row : getRows()) {
            List<String> column = new Transposer<>(Arrays.asList(row)).transpose().get(0);
            result.add(column);
        }
        return result;
    }

    public List<String[]> standardize() {
        List<String[]> result = new ArrayList<>();
        for (List<String> column : getRows()) {
            result.add(column.toArray(new String[0]));
        }
        return result;
    }
}
