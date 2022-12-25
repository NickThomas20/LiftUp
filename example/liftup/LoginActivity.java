package com.example.liftup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.Quote;
import com.example.liftup.model.User;

import static com.example.liftup.api.ApiClientFactory .*;

public class LoginActivity extends AppCompatActivity {

    private static final String INVALID_USERNAME_TEXT = "Invalid username.";
    private static final String INVALID_PASSWORD_TEXT = "Incorrect password.";

    private Button _loginButton;

    private EditText _usernameEditText;
    private EditText _passwordEditText;

    private TextView _invalidUsernameTextView;
    private TextView _invalidPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _loginButton = findViewById(R.id.login_button_login);

        _usernameEditText = findViewById(R.id.login_login_username);
        _passwordEditText = findViewById(R.id.login_login_password);

        _invalidUsernameTextView = findViewById(R.id.textView_login_invalidUsername);
        _invalidPasswordTextView = findViewById(R.id.textView_login_invalidPassword);

        _loginButton.setOnClickListener(v -> tryLogin());

        Quote newQuote = new Quote();
        newQuote.setQuoteID(5);
        String a;

        GetQuoteApi().getRandomQuote().enqueue(new SlimCallback<Quote>(response -> {

            newQuote.setQuote(response.getQuote());
            Log.d("test", response.getQuote());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("A", "A", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "A")
                .setSmallIcon(R.drawable.profile)
                .setContentTitle(newQuote.getQuote())
                .setContentText(newQuote.getQuote())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(LoginActivity.this);
        managerCompat.notify(1, builder.build());
//THIS
        }));
    }


    /**
     * Attempts to login, and moves to the next activity if successful.
     */
    private void tryLogin()
    {
        String username = _usernameEditText.getText().toString();
        String password = _passwordEditText.getText().toString();

        //get user data from database
        GetUserApi().getSpecificUser(username).enqueue(new SlimCallback<User>(response -> {
            if(response.getUsername().compareTo(username) == 0 && response.getPassword().compareTo(password) == 0)
            {
                //if code is here, we have a valid username and password
                login(response);
            }

        }, "CustomTagForFirstApi"));
    }

    /**
     * Sends the user to the next activity.
     */
    private void login(User user)
    {
        Session.setUser(user);

        //user is valid, so lets move on to profile
        ActivityLoader.loadActivity(this, "ProfileActivity");
    }
}