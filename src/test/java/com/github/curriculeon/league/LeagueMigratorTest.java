package com.github.curriculeon.league;

import org.junit.Test;

/**
 * @author leonhunter
 * @created 05/29/2020 - 8:44 PM
 */
public class LeagueMigratorTest {
    @Test
    public void test() {
        LeagueMigrator maleMigrator = new LeagueMigrator("men-league.xlsx", "male");
        LeagueMigrator femaleMigrator = new LeagueMigrator("women-league.xlsx", "female");
        System.out.println(maleMigrator.getSqlQuery());
        System.out.println(femaleMigrator.getSqlQuery());
    }
}
