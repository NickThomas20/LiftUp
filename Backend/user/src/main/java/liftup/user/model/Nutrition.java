package liftup.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Nutrition {
    @Id
    private String username;
    private int caloriesConsumed;
    private int proteinConsumed;
    private int fatConsumed;
    private int carbsConsumed;

    public Nutrition() {
    }

    public Nutrition(String username, int caloriesConsumed, int proteinConsumed, int fatConsumed, int carbsConsumed) {
        this.username = username;
        this.caloriesConsumed = caloriesConsumed;
        this.proteinConsumed = proteinConsumed;
        this.fatConsumed = fatConsumed;
        this.carbsConsumed = carbsConsumed;
    }

    public String getUsername() {
        return username;
    }

    public int getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public int getProteinConsumed() {
        return proteinConsumed;
    }

    public int getFatConsumed() {
        return fatConsumed;
    }

    public int getCarbsConsumed() { return  carbsConsumed; }
}
