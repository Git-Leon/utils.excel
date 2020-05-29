package com.github.curriculeon.student;

/**
 * @author leonhunter
 * @created 05/29/2020 - 6:51 PM
 */
public class League {
    String countryName;
    String leagueName;
    Long id;

    public League(String countryName, String leagueName, Long id) {
        this.countryName = countryName;
        this.leagueName = leagueName;
        this.id = id;
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

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "League{" +
            "countryName='" + countryName + '\'' +
            ", leagueName='" + leagueName + '\'' +
            ", id=" + id +
            '}';
    }
}
