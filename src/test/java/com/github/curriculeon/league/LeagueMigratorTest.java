package com.github.curriculeon.league;

import org.junit.Test;

/**
 * @author leonhunter
 * @created 05/29/2020 - 8:44 PM
 */
public class LeagueMigratorTest {
    @Test
    public void test() {
        LeagueMigrator migrator = new LeagueMigrator("men-league.xlsx", "male");
        System.out.println(migrator.getSqlQuery());
    }
}
