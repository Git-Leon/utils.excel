package com.github.curriculeon.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author leonhunter
 * @created 01/25/2020 - 12:00 AM
 * used to invert axes of a table
 */
public class Transposer<SomeType> {
    private List<List<SomeType>> table;

    public Transposer(List<List<SomeType>> table) {
        this.table = table;
    }

    public List<List<SomeType>> transpose() {
        List<List<SomeType>> ret = new ArrayList<List<SomeType>>();
        final int N = table.get(0).size();
        for (int i = 0; i < N; i++) {
            List<SomeType> col = new ArrayList<SomeType>();
            for (List<SomeType> row : table) {
                col.add(row.get(i));
            }
            ret.add(col);
        }
        return ret;
    }

    public static List<List<String>> normalize(List<String[]> rows) {
        List<List<String>> result = new ArrayList<>();
        for (String[] row : rows) {
            result.add(Arrays.asList(row));
        }
        return result;
    }
}
