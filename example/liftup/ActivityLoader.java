package com.example.liftup;

import android.content.Context;
import android.content.Intent;

/**
 * Responsible for moving the user from activity to activity.
 */
public class ActivityLoader {

    private static final String _qualifiedNamePrefix = "com.example.liftup.";

    /**
     * Gets the corresponding class from the given name within the String.
     * @param cName The name of the class.
     * @return The corresponding class.
     * @throws ClassNotFoundException if the corresponding class does not exist.
     */
    private static Class getClassFromName(String cName) throws ClassNotFoundException
    {
        return Class.forName(_qualifiedNamePrefix + cName);
    }

    /**
     * Loads the Activity that belongs to the corresponding class from the given class name.
     * @param from The current instance of the active Activity.
     * @param cName The name of the class of the Activity that is to be loaded.
     */
    public static void loadActivity(Context from, String cName)
    {
        Class c;

        try{
            c = getClassFromName(cName);
        } catch (ClassNotFoundException e)
        {
            //do nothing if class not found
            return;
        }

        //class found, load the activity
        loadActivity(from, c);
    }

    /**
     * Loads the Activity that belongs to the given class.
     * @param from The current instance of the active Activity.
     * @param c The class that contains the Activity that is to be loaded.
     */
    public static void loadActivity(Context from, Class c)
    {
        from.startActivity(new Intent(from, c));
        for(int i = 0; i < 10; i++)
        {
            buffer();
        }
    }



    public static void buffer(){

    }
}
