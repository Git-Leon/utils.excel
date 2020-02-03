package com.github.curriculeon.utils.string.stringevaluator;

import com.github.curriculeon.utils.string.StringEvaluator;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leonhunter
 * @created 02/02/2020 - 8:06 PM
 */
public class GetSimilarity {
    //given
    private void test(String baseString, String stringToCompareAgainst, Double expectedValue) {
        StringEvaluator stringEvaluator = new StringEvaluator(baseString);

        // when
        Double actualValue = stringEvaluator.getSimilarity(stringToCompareAgainst);

        //then
        Assert.assertEquals(expectedValue, actualValue, 0);
    }

    @Test
    public void test0() {
        test("", "", 1.0);
    }

    @Test
    public void test1() {
        test("1234567890", "1234567890", 1.0);
    }

    @Test
    public void test2() {
        test("1234567890", "1", .1);
    }

    @Test
    public void test3() {
        test("1234567890", "123", .3);
    }

    @Test
    public void test4() {
        test("1234567890", "1234567", .7);
    }

    @Test
    public void test5() {
        test("1234567890", "1234567980", .8);
    }

    @Test
    public void test6() {
        test("47/2010", "472010", .857);
    }

    @Test
    public void test7() {
        test("47/2010", "472011", .714);
    }

    @Test
    public void test8() {
        test("47/2010", "AB.CDEF", 0.0);
    }

    @Test
    public void test9() {
        test("47/2010", "4B.CDEFG", .125);
    }

    @Test
    public void test10() {
        test("47/2010", "AB.CDEFG", 0.0);
    }

    @Test
    public void test11() {
        test("The quick fox jumped", "The fox jumped", .7);
    }

    @Test
    public void test12() {
        test("The quick fox jumped", "The fox", .35);
    }

    @Test
    public void test13() {
        test("kitten", "sitting", .571);
    }
}
