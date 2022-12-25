package com.example.liftup;

import static com.example.liftup.api.ApiClientFactory.GetNutritionApi;
import static com.example.liftup.api.ApiClientFactory.GetStatsApi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.Nutrition;
import com.example.liftup.model.Stat;
import com.example.liftup.model.User;

import java.util.List;

public class showNutrition extends AppCompatActivity {

    private TextView calories;
    private TextView protein;
    private TextView fat;
    private TextView carbs;
    private Button createNut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_nutrition);
        User user = Session.getUser();
        createNut = findViewById(R.id.createNut);
        calories = findViewById(R.id.caloriesText);
        protein = findViewById(R.id.protienText);
        fat = findViewById(R.id.FatText);
        carbs = findViewById(R.id.CarbsText);

        GetNutritionApi().getUsersNutrition(user.getUsername()).enqueue(new SlimCallback<Nutrition>(response -> {
            Log.d("test", "Call al" );
            int cal = response.getCaloriesConsumed();
            int pro = response.getProteinConsumed();
            int fatt = response.getFatConsumed();
            int carbss = response.getCarbsConsumed();

            calories.setText(String.valueOf(cal));
            protein.setText(String.valueOf(pro));
            fat.setText(String.valueOf(fatt));
            carbs.setText(String.valueOf(carbss));

        }));

        createNut.setOnClickListener(v -> {
            ActivityLoader.loadActivity(this, addNutrition.class);
        });
    }
}