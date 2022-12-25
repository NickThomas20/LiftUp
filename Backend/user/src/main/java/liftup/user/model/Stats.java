package liftup.user.model;

import javax.persistence.*;

@Entity
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statId;
    private String statName;
    private String value;
    private boolean isOn;
    private String username;

    public Stats() {
    }

    public int getStatId() {
        return statId;
    }

    public String getStatName() {
        return statName;
    }

    public String getValue() {
        return value;
    }

    public boolean getIsOn() {
        return isOn;
    }

    public String getUsername() {
        return username;
    }
}
