package com.example.liftup.model;

import androidx.annotation.Nullable;

import com.example.liftup.Utility;

import java.util.Date;

public abstract class DateBase {
    public abstract String getUsername();

    public abstract Date getDateAsDate();

    public abstract boolean isCompleted();

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj != null && obj.getClass() == getClass())
        {
            DateBase d = (DateBase)obj;

            return getUsername().compareTo(d.getUsername()) == 0 && Utility.compareDates(getDateAsDate(), d.getDateAsDate()) == 0;
        }
        return false;
    }
}
