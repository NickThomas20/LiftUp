package com.example.liftup.model;

/**
 * Holds data for one specific statistic for a user.
 */
public class Stat {

    private int statId;
    private String statName;
    private String value;
    private boolean isOn;
    private String username;

    public Stat() {

    }

    public Stat( String statName, String value, boolean isOn, String username) {
        this.statName = statName;
        this.value = value;
        this.isOn = isOn;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatId() {
        return statId;
    }

    public void setStatId(int statId) {
        this.statId = statId;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
