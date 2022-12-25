package com.example.liftup.model;


public class TrainerApplication {
    private String username;
    private int YearsActive;
    private String trainerSpeciality;

    public TrainerApplication() {
    }

    public TrainerApplication(String username, int yearsActive, String trainerSpeciality) {
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

    public String getSpeciality() {
        return trainerSpeciality;
    }
    public void setTrainerSpeciality(String trainerSpeciality) {
        this.trainerSpeciality = trainerSpeciality;
    }

}
