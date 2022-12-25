package com.example.liftup;

import static com.example.liftup.api.ApiClientFactory.GetUserApi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.User;

public class EditProfileActivity extends AppCompatActivity {

    private EditText _fnameEditText;
    private EditText _lnameEditText;
    private EditText _emailEditText;
    private EditText _passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        _fnameEditText = findViewById(R.id.editText_editProfile_fname);
        _lnameEditText = findViewById(R.id.editText_editProfile_lname);
        _emailEditText = findViewById(R.id.editText_editProfile_email);
        _passwordEditText = findViewById(R.id.editText_editProfile_password);

        Button cancelButton = findViewById(R.id.button_editProfile_cancel);
        cancelButton.setOnClickListener((v) -> {
            back();
        });

        Button saveButton = findViewById(R.id.button_editButton_save);
        saveButton.setOnClickListener((v) -> {
            //get data
            User user = Session.getUser();

            user.setFname(_fnameEditText.getText().toString());
            user.setLname(_lnameEditText.getText().toString());
            user.setEmail(_emailEditText.getText().toString());
            user.setPassword(_passwordEditText.getText().toString());

            //save it
            GetUserApi().postUserByPath(user).enqueue(new SlimCallback<Validation>(response -> {
                Log.d("CustomTag", "Updated user: " + response.toString());

                if(response.isValid())
                {
                    back();
                }
            }, "CustomTagForFirstApi"));
        });
    }

    /**
     * Goes back to the previous activity (ProfileActivity)
     */
    private void back()
    {
        ActivityLoader.loadActivity(this, "ProfileActivity");
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        //load session user data
        User user = Session.getUser();

        _fnameEditText.setText(user.getFname());
        _lnameEditText.setText(user.getLname());
        _emailEditText.setText(user.getEmail());
        _passwordEditText.setText(user.getPassword());
    }
}