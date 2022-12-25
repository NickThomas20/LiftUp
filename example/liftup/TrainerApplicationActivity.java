package com.example.liftup;

import static com.example.liftup.api.ApiClientFactory.GetTrainerApplicationApi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.TrainerApplication;

public class TrainerApplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_application);

        Button submitButton = findViewById(R.id.trainerApp_button_submit);

        submitButton.setOnClickListener(v ->
        {
            EditText yearsActiveEditText = findViewById(R.id.trainerApp_editTextNumber_button);
            int yearsActive = Integer.parseInt(yearsActiveEditText.getText().toString());

            if(yearsActive < 0)
            {
                // must have >= 0 years
                return;
            }

            EditText specialtyEditText = findViewById(R.id.trainerApp_editText_specialty);
            String specialty = specialtyEditText.getText().toString();

            if(Utility.isStringEmptyOrWhitespace(specialty))
            {
                // must have something written down
                return;
            }

            // actually submit
            TrainerApplication application = new TrainerApplication(Session.getUsername(), yearsActive, specialty);

            GetTrainerApplicationApi().addTrainerApp(application).enqueue(new SlimCallback<Validation>(response -> {
                ActivityLoader.loadActivity(this, "ProfileActivity");
            }, "CalendarApi"));
        });
    }
}