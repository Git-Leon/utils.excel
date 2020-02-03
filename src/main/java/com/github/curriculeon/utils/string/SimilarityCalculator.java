package com.github.curriculeon.utils.string;

import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;

/**
 * @author leonhunter
 * @created 02/02/2020 - 7:47 PM
 * https://stackoverflow.com/questions/955110/similarity-string-comparison-in-java/16018452
 */
public class SimilarityCalculator {
    private final String baseString;

    public SimilarityCalculator(String baseString) {
        this.baseString = baseString;
    }

    public String getBaseString() {
        return baseString;
    }

    /**
     * Calculates the similarity (a number within 0 and 1) between two strings.
     */
    public double getSimilarity(String s2) {
        return new JaroWinklerDistance().apply(baseString, s2);
    }


}
