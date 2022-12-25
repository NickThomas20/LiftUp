package com.example.liftup;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

public class Notify {
    public static void show(@NonNull View view, @NonNull String message)
    {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
