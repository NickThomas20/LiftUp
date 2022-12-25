package liftup.user.model;

import java.io.Serializable;
import java.util.Objects;

public class UserDateId implements Serializable {
    private String username;
    private String date;

    public UserDateId() {
    }

    public UserDateId(String username, String date) {
        this.username = username;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDateId)) return false;
        UserDateId that = (UserDateId) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getDate());
    }
}
