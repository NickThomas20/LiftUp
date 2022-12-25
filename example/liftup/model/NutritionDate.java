package com.example.liftup.model;

import java.util.Date;

public class NutritionDate extends DateBase {
    private String username;
    private String date;
    private int calories;
    private int protein;
    private int fat;
    private int carbs;

    public NutritionDate() {
    }

    public NutritionDate(String username, Date date, int calories, int protein, int fat, int carbs) {
        this.username = username;
        this.date = date.toString();
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    @Override
    public Date getDateAsDate()
    {
        return WorkoutDate.parseDate(date);
    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

}
