package com.github.curriculeon;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author leonhunter
 * @created 01/24/2020 - 11:58 PM
 */
public class CSVParser {
    private List<List<String>> columns;

    public CSVParser(File csvFile) {
        try {
            CSVReader reader = new CSVReader(new FileReader(csvFile.getAbsolutePath()));
            this.columns = transpose(normalize(reader.readAll()));
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public List<String> getColumn(Integer columnNumber) {
        return columns.get(columnNumber);
    }

    public List<String> getRow(Integer columnNumber) {
        return transpose(columns).get(columnNumber);
    }

    private List<List<String>> normalize(List<String[]> rows) {
        List<List<String>> result = new ArrayList<>();
        for (String[] row : rows) {
            result.add(Arrays.asList(row));
        }
        return result;
    }

    private <T> List<List<T>> transpose(List<List<T>> table) {
        List<List<T>> ret = new ArrayList<List<T>>();
        final int N = table.get(0).size();
        for (int i = 0; i < N; i++) {
            List<T> col = new ArrayList<T>();
            for (List<T> row : table) {
                col.add(row.get(i));
            }
            ret.add(col);
        }
        return ret;
    }
}

