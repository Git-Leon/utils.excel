package com.github.curriculeon.utils.string.stringevaluator;

import com.github.curriculeon.utils.StringEvaluator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author leonhunter
 * @created 02/02/2020 - 8:07 PM
 */
public class GetMostSimilar {
    //given
    private void test(List<String> stringsToCompareAgainst, String baseString, String expected) {
        StringEvaluator stringEvaluator = new StringEvaluator(baseString);

        // when
        String actual = stringEvaluator.getMostSimilar(stringsToCompareAgainst);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test0() {
        test(Arrays.asList(
                "",
                "-",
                "1"), "", "");
    }

    @Test
    public void test1() {
        test(Arrays.asList(
                "1234567890",
                "123456789",
                "12345678",
                "1234567",
                "123456"), "1234567890", "1234567890");
    }

    @Test
    public void test2() {
        test(Arrays.asList(
                "123456789",
                "12345678",
                "1234567",
                "123456"), "1234567890", "123456789");
    }


    @Test
    public void test3() {
        test(Arrays.asList("Relational Database Skills (Mar",
                "Front-end Development",
                "Grades Parsed From Canvas",
                "java-developer-philly-rubric-te",
                "Server Pages Fundamentals - Ser",
                "Project Management Methodologie",
                "Main",
                "JDBC, ORM, and Unit Testing Fun",
                "In-class Participation",
                "Programming (Java)",
                "Case Study"), "Relational", "Relational Database Skills (Mar");
    }


    @Test
    public void test4() {
        test(Arrays.asList("Relational Database Skills (Mar",
                "Front-end Development",
                "Grades Parsed From Canvas",
                "java-developer-philly-rubric-te",
                "Server Pages Fundamentals - Ser",
                "Project Management Methodologie",
                "Main",
                "JDBC, ORM, and Unit Testing Fun",
                "In-class Participation",
                "Programming (Java)",
                "Case Study"), "Front end", "Front-end Development");
    }



    @Test
    public void test5() {
        test(Arrays.asList("Relational Database Skills (Mar",
                "Front-end Development",
                "Grades Parsed From Canvas",
                "java-developer-philly-rubric-te",
                "Server Pages Fundamentals - Ser",
                "Project Management Methodologie",
                "Main",
                "JDBC, ORM, and Unit Testing Fun",
                "In-class Participation",
                "Programming (Java)",
                "Case Study"), "Grades extracted from Canvas", "Grades Parsed From Canvas");
    }


    @Test
    public void test6() {
        test(Arrays.asList("Relational Database Skills (Mar",
                "Front-end Development",
                "Grades Parsed From Canvas",
                "java-developer-philly-rubric-te",
                "Server Pages Fundamentals - Ser",
                "Project Management Methodologie",
                "Main",
                "JDBC, ORM, and Unit Testing Fun",
                "In-class Participation",
                "Programming (Java)",
                "Case Study"), "JDBC", "JDBC, ORM, and Unit Testing Fun");
    }



    @Test
    public void test7() {
        test(Arrays.asList("Relational Database Skills (Mar",
                "Front-end Development",
                "Grades Parsed From Canvas",
                "java-developer-philly-rubric-te",
                "Server Pages Fundamentals - Ser",
                "Project Management Methodologie",
                "Main",
                "JDBC, ORM, and Unit Testing Fun",
                "In-class Participation",
                "Programming (Java)",
                "Case Study"), "Programming", "Programming (Java)");
    }


    @Test
    public void test8() {
        test(Arrays.asList("Relational Database Skills (Mar",
                "Front-end Development",
                "Grades Parsed From Canvas",
                "java-developer-philly-rubric-te",
                "Server Pages Fundamentals - Ser",
                "Project Management Methodologie",
                "Main",
                "JDBC, ORM, and Unit Testing Fun",
                "In-class Participation",
                "Programming (Java)",
                "Case Study"), "Participate", "In-class Participation");
    }




    @Test
    public void test9() {
        test(Arrays.asList("Relational Database Skills (Mar",
                "Front-end Development",
                "Grades Parsed From Canvas",
                "java-developer-philly-rubric-te",
                "Server Pages Fundamentals - Ser",
                "Project Management Methodologie",
                "Main",
                "JDBC, ORM, and Unit Testing Fun",
                "In-class Participation",
                "Programming (Java)",
                "Case Study"), "Project Management Methodologies Current Score", "Project Management Methodologie");
    }


    @Test
    public void test10() {
        test(Arrays.asList("Relational Database Skills (Mar",
                "Front-end Development",
                "Grades Parsed From Canvas",
                "java-developer-philly-rubric-te",
                "Server Pages Fundamentals - Ser",
                "Project Management Methodologie",
                "Main",
                "JDBC, ORM, and Unit Testing Fun",
                "In-class Participation",
                "Programming (Java)",
                "Case Study"), "AD - KBA - JDBC/ORM/Unit Testing (4069)", "JDBC, ORM, and Unit Testing Fun");
    }


    @Test
    public void test11() {
        test(Arrays.asList("Relational Database Skills (Mar",
                "Front-end Development",
                "Grades Parsed From Canvas",
                "java-developer-philly-rubric-te",
                "Server Pages Fundamentals - Ser",
                "Project Management Methodologie",
                "Main",
                "JDBC, ORM, and Unit Testing Fun",
                "In-class Participation",
                "Programming (Java)",
                "Case Study"), "AD - KBA - Relational Databases and SQL Final Score", "Relational Database Skills (Mar");
    }
}
