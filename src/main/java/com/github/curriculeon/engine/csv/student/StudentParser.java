package com.github.curriculeon.engine.csv.student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/07/2020 - 3:13 PM
 */
public class StudentParser {
    private List<List<String>> rows;

    public StudentParser(List<List<String>> rows) {
        this.rows = rows;
    }

    public List<Student> getStudents() {
        List<Student> result = new ArrayList<>();
        for (List<String> row : rows) {
            String studentName = row.get(0);
            String studentId = row.get(1);
            Student student = new Student(studentId, studentName);
            StudentValidator validator = new StudentValidator(student);
            if (validator.validate()) {
                result.add(student);
            }
        }
        return result;
    }
}
