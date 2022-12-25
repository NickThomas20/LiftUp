package com.example.liftup;

import com.example.liftup.model.User;

/**
 * Holds the data for the current session the user is in.
 */
public class Session {
    /**
     * The current user data. Null if not logged in.
     */
    private static User _user = null;


    /**
     * Gets the current user. Null if not logged in.
     * @return
     */
    public static User getUser()
    {
        if(_user == null)
        {
            User user = new User();
            _user = user;
        }

        return _user;
    }

    public static String getUsername()
    {
        return _user.getUsername();
    }

    /**
     * Sets the current user of the session.
     * @param user
     */
    public static void setUser(User user)
    {
        _user = user;
    }

    /**
     * Is a user logged in?
     * @return true of the stored user value is not null.
     */
    public static boolean isLoggedIn()
    {
        return _user != null;
    }


}
