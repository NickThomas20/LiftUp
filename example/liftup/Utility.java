package com.example.liftup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class to help provide functionality that may be used within multiple classes.
 */
public class Utility
{
    /**
     * Hashes the given string.
     * @param str the string to hash.
     * @return a hashed version of the string.
     */
    public static String hashString(String str)
    {
        //for now
        return str;
    }

    /**
     * Determines if the given string is null or whitespace.
     * @param str the string to check.
     * @return true if the string is null or full of whitespace.
     */
    public static boolean isStringEmptyOrWhitespace(String str)
    {
        return str == null || str.trim().length() == 0;
    }

    public static String join(String delimiter, Object... objects)
    {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < objects.length; i++)
        {
            sb.append(objects[i].toString());

            if(i < objects.length - 1)
            {
                sb.append(delimiter);
            }
        }

        return  sb.toString();
    }

    public static int compareDates(Date left, Date right)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        return format.format(left).compareTo(format.format(right));
    }

    public static Date parseDate(String date)
    {
        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        Date d;
        try
        {
            d = parser.parse(date);
        } catch (ParseException ex)
        {
            d = null;
        }

        return d;
    }
}
