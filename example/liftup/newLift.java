package com.example.liftup;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.liftup.api.ApiClientFactory.GetLiftsApi;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.Lift;

public class newLift extends AppCompatActivity {

    private EditText liftName;
    private EditText repAmmount;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lift);

        liftName = findViewById(R.id.liftNameField);
        repAmmount = findViewById(R.id.repsField);
        create = findViewById(R.id.button);

        create.setOnClickListener(v -> {
            CreateLift();
        });

    }


    private void CreateLift(){

        Lift lift = new Lift( liftName.getText().toString(), Integer.parseInt(repAmmount.getText().toString()));
            GetLiftsApi().addLifts(lift).enqueue(new SlimCallback<>(response -> {
                Log.d("in create", lift.getExercise().toString());
        }, "CustomTag"));

    }
}