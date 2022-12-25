package com.example.liftup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //this is testing git f
    private Button _loginButton;

    private Button _registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _loginButton = findViewById(R.id.button_main_login);
        _registerButton = findViewById(R.id.button_main_register);

        _loginButton.setOnClickListener((v) -> {
            ActivityLoader.loadActivity(this, "LoginActivity");
        });

        _registerButton.setOnClickListener((v) -> {
            ActivityLoader.loadActivity(this, "RegisterActivity");
        });
    }

    /**
     * Checks if the user is already logged in. If so, the user is directed to the next page.
     */
    private void checkLoggedIn()
    {
        if(isLoggedIn())
        {
            ActivityLoader.loadActivity(this, "ProfileActivity");
        }
    }

    /**
     * Checks if there is already an account signed in.
     * @return
     */
    private boolean isLoggedIn()
    {
        //TODO: check local stored user data for logged in account
        return false;
    }
}