package com.example.liftup.model;

/**
 * Holds nutrition data for a user.
 */
public class Nutrition {

    private String username;
    private int caloriesConsumed;
    private int proteinConsumed;
    private int fatConsumed;
    private int carbsConsumed;

    public Nutrition() {
    }

    public Nutrition(String username,
                     int caloriesConsumed,
                     int proteinConsumed,
                     int fatConsumed,
                     int carbsConsumed)
    {
        this.username = username;
        this.caloriesConsumed = caloriesConsumed;
        this.proteinConsumed = proteinConsumed;
        this.fatConsumed = fatConsumed;
        this.carbsConsumed = carbsConsumed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public void setCaloriesConsumed(int caloriesConsumed) {
        this.caloriesConsumed = caloriesConsumed;
    }

    public int getProteinConsumed() {
        return proteinConsumed;
    }

    public void setProteinConsumed(int proteinConsumed) {
        this.proteinConsumed = proteinConsumed;
    }

    public int getFatConsumed() {
        return fatConsumed;
    }

    public void setFatConsumed(int fatConsumed) {
        this.fatConsumed = fatConsumed;
    }

    public int getCarbsConsumed() {
        return carbsConsumed;
    }

    public void setCarbsConsumed(int carbsConsumed) {
        this.carbsConsumed = carbsConsumed;
    }
}
