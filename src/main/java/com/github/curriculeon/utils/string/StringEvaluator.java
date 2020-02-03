package com.github.curriculeon.utils.string;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author leonhunter
 * @created 02/02/2020 - 7:40 PM
 */
public class StringEvaluator {
    private final SimilarityCalculator stringCalculator;

    public StringEvaluator(String baseString) {
        this.stringCalculator = new SimilarityCalculator(baseString);
    }

    public Double getSimilarity(String stringToCompareAgainst) {
        return stringCalculator.getSimilarity(stringToCompareAgainst);
    }

    public Map<String, Double> getSimilarityMap(List<String> stringsToCompareAgainst) {
        Map<String, Double> map = new HashMap<>();
        for (String stringToCompareAgainst : stringsToCompareAgainst) {
            map.put(stringToCompareAgainst, getSimilarity(stringToCompareAgainst));
        }
        return map;
    }

    public String getMostSimilar(List<String> stringsToCompareAgainst) {
        Map<String, Double> similarityMap = getSimilarityMap(stringsToCompareAgainst);
        Collection<Double> values = similarityMap.values();
        List<Double> ascendingValues = new ArrayList<>(new TreeSet<>(values));
        Collections.reverse(ascendingValues);
        List<Double> descendingValues = ascendingValues;
        Double highestSimilarity = ascendingValues.get(0);
        for(String key : similarityMap.keySet()) {
            Double value = similarityMap.get(key);
            if(value.equals(highestSimilarity)) {
                return key;
            }
        }
        return null;
    }

}
