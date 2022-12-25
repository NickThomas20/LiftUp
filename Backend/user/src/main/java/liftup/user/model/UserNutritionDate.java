package liftup.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(UserDateId.class)
public class UserNutritionDate {
    @Id
    private String username;
    @Id
    private String date;
    private int calories;
    private int protein;
    private int fat;
    private int carbs;

    public UserNutritionDate() {
    }

    public UserNutritionDate(String username, String date, int calories, int protein, int fat, int carbs) {
        this.username = username;
        this.date = date;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
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
}
