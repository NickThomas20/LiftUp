package com.example.liftup;

import static com.example.liftup.api.ApiClientFactory.GetUserApi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.User;

public class RegisterActivity extends AppCompatActivity {

    private Button _registerButton;

    private EditText _firstNameEditText;
    private EditText _lastNameEditText;
    private EditText _usernameEditText;
    private EditText _emailEditText;
    private EditText _passwordEditText;
    private EditText _confirmPasswordEditText;

    private TextView _invalidFirstNameTextView;
    private TextView _invalidLastNameTextView;
    private TextView _invalidUsernameTextView;
    private TextView _invalidEmailTextView;
    private TextView _invalidPasswordTextView;
    private TextView _invalidConfirmPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //get references to all input fields, buttons, etc.
        _firstNameEditText = findViewById(R.id.editTextTextPersonName_firstName);
        _lastNameEditText = findViewById(R.id.editTextTextPersonName_lastName);
        _usernameEditText = findViewById(R.id.login_login_username);
        _emailEditText = findViewById(R.id.editTextTextEmailAddress_email);
        _passwordEditText = findViewById(R.id.login_login_password);
        _confirmPasswordEditText = findViewById(R.id.editTextTextPassword_confirmPassword);

        _invalidFirstNameTextView = findViewById(R.id.textView_register_invalidFirstName);
        _invalidLastNameTextView = findViewById(R.id.textView_register_invalidLastName);
        _invalidUsernameTextView = findViewById(R.id.textView_register_invalidUsername);
        _invalidEmailTextView = findViewById(R.id.textView_register_invalidEmail);
        _invalidPasswordTextView = findViewById(R.id.textView_register_invalidPassword);
        _invalidConfirmPasswordTextView = findViewById(R.id.textView_register_invalidConfirmPassword);

        _registerButton = findViewById(R.id.login_button_login);

        //set click event for the button
        _registerButton.setOnClickListener(v -> tryRegister());
    }

    /**
     * Tries to register the user with the database.
     * Only sends data to the database if the data is valid and does not already exist in the database.
     */
    private void tryRegister()
    {
        boolean canRegister = true;

        String firstName = _firstNameEditText.getText().toString();
        String lastName = _lastNameEditText.getText().toString();
        canRegister &= check(checkName(firstName), _invalidFirstNameTextView, "Enter a first name.");
        canRegister &= check(checkName(lastName), _invalidLastNameTextView, "Enter a last name.");
        String username = _usernameEditText.getText().toString();
        String email = _emailEditText.getText().toString();
        String password = _passwordEditText.getText().toString();
        canRegister &= check(password.compareTo(_confirmPasswordEditText.getText().toString()) == 0, _invalidConfirmPasswordTextView, "Mismatched passwords.");

        //if still true, all was passed, we can register
        if(canRegister)
        {
            //create a new user
            User user = new User(username, firstName, lastName, email, password);

            //register user and move on
            GetUserApi().postUserByPath(user).enqueue(new SlimCallback<Validation>(response -> {
                if(response.isValid())
                {
                    // if valid, user was added to the database
                    register(user);
                }
            }, "CustomTagForFirstApi"));
        }
    }

    /**
     * Given the result, the given text view will display the message if the result is a fail.
     * @param result the result of the check.
     * @param textView the error textview to display the error message.
     * @param invalidMessage the message letting the user know what they did wrong.
     * @return the given result value.
     */
    private boolean check(boolean result, TextView textView, String invalidMessage)
    {
        if(result)
        {
            textView.setText("");
        } else {
            textView.setText(invalidMessage);
        }

        return result;
    }

    /**
     * Register data into the database, then move to the next scene.
     */
    private void register(User user)
    {
        Session.setUser(user);

        //move on to next screen
        ActivityLoader.loadActivity(this, "ProfileActivity");
    }

    /**
     * Checks if the given name is valid.
     * Valid names have at least 1 character that is not whitespace.
     * @param name the name to be checked.
     * @return true if the name is valid.
     */
    private boolean checkName(String name)
    {
        return name.trim().length() > 0;
    }
}