package com.github.curriculeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author leonhunter
 * @created 01/25/2020 - 12:00 AM
 */
public class Transposer {
    private List<List<String>> matrix;

    public Transposer(List<List<String>> matrix) {
        this.matrix = matrix;
    }

    public static List<List<String>> normalize(List<String[]> rows) {
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
