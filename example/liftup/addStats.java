package com.example.liftup;

import static com.example.liftup.api.ApiClientFactory.GetStatsApi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.Stat;
import com.example.liftup.model.User;

public class addStats extends AppCompatActivity {

    private TextView statName;
    private TextView statValue;
    private Button addStat;
    User user = Session.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stats);

        statName = findViewById(R.id.calories);
        statValue = findViewById(R.id.nutValue);
        addStat = findViewById(R.id.addNut);


        addStat.setOnClickListener(v -> {

            addStat();

        });

    }

    private void addStat(){
        Stat newstat = new Stat(statName.getText().toString(), statValue.getText().toString(), true, user.getUsername());

        GetStatsApi().addNewStat(newstat).enqueue(new SlimCallback<Stat>(response -> {
            Log.d("add stat", "stat added" );
        }));
    }
}