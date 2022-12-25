package com.example.liftup;

public class Validation {
    boolean valid;
    String message;

    public Validation()
    {

    }

    public Validation(boolean v, String m)
    {
        valid = v;
        message = m;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Validation{" +
                "valid=" + valid +
                ", message='" + message + '\'' +
                '}';
    }
}
