package com.github.curriculeon.utils.string.stringevaluator;

import com.github.curriculeon.utils.string.StringEvaluator;
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
}
