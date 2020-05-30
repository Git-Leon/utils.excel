package com.github.curriculeon.league;

/**
 * @author leonhunter
 * @created 05/29/2020 - 6:51 PM
 */
public class League {
    private static Long numberOfLeagues = 0L;
    private final Long id;
    private String countryName;
    private String leagueName;
    private String gender;

    public League(String countryName, String leagueName, String gender) {
        this.countryName = countryName;
        this.leagueName = leagueName;
        this.id = numberOfLeagues++;
        this.gender = gender;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "League{" +
            "countryName='" + countryName + '\'' +
            ", leagueName='" + leagueName + '\'' +
            ", id=" + id +
            '}';
    }

    public String getInsertionStatement() {
        return new StringBuilder()
            .append("INSERT INTO obc.league(id, country, name, gender) VALUES(")
            .append(getId())
            .append(",'")
            .append(getCountryName())
            .append("','")
            .append(getLeagueName())
            .append("','")
            .append(getGender())
            .append("');")

            .toString();
    }

    public static String getCreationStatement() {
        return "CREATE TABLE IF NOT EXISTS obc.league(id INT PRIMARY KEY, country TEXT NOT NULL, name TEXT NOT NULL);";
    }
}
