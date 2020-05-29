package com.github.git_leon.utils.stringevaluator;

import com.github.git_leon.utils.StringEvaluator;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

/**
 * @author leonhunter
 * @created 02/02/2020 - 8:06 PM
 */
public class GetSimilarityMap {
    //given
    private void test(String baseString, List<String> stringsToCompareAgainst, Map<String, Double> expected) {
        StringEvaluator stringEvaluator = new StringEvaluator(baseString);

        // when
        Map<String, Double> actualValue = stringEvaluator.getSimilarityMap(stringsToCompareAgainst);

        //then
        Assert.assertEquals(expected, actualValue);
    }

}
