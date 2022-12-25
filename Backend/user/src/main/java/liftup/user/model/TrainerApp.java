package liftup.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TrainerApp {
    @Id
    private String username;
    private int YearsActive;
    private String trainerSpeciality;

    public TrainerApp() {
    }

    public TrainerApp(String username, int yearsActive, String trainerSpeciality) {
        this.username = username;
        YearsActive = yearsActive;
        this.trainerSpeciality = trainerSpeciality;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getYearsActive() {
        return YearsActive;
    }
    public void setYearsActive(int yearsActive) {
        YearsActive = yearsActive;
    }

    public String getTrainerSpeciality() {
        return trainerSpeciality;
    }
    public void setTrainerSpeciality(String trainerSpeciality) {
        this.trainerSpeciality = trainerSpeciality;
    }
}
