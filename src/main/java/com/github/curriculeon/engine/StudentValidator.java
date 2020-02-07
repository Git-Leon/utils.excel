package com.github.curriculeon.engine;

import java.util.Arrays;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/07/2020 - 2:31 PM
 */
public class StudentValidator {
    Student student;

    public StudentValidator(Student student) {
        this.student = student;
    }

    private Boolean validateId() {
        try {
            Integer.parseInt(student.getId());
            return true;
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

    private Boolean validateName() {
        List<String> invalidChars = Arrays.asList("!@#$%^&*()_\t\n\r".split(""));
        for(String invalidChar : invalidChars) {
            if(student.getName().contains(invalidChar)) {
                return false;
            }
        }
        return true;
    }

    public boolean validate() {
        return validateId() &&
                validateName();
    }
}
