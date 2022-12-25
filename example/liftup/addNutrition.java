package com.example.liftup;

import static com.example.liftup.api.ApiClientFactory.GetNutritionApi;
import static com.example.liftup.api.ApiClientFactory.GetStatsApi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.Nutrition;
import com.example.liftup.model.Stat;
import com.example.liftup.model.User;

public class addNutrition extends AppCompatActivity {

    private TextView calories;
    private TextView protein;
    private TextView fat;
    private TextView carbs;
    private Button addNut;
    User user = Session.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nutrition);

        calories = findViewById(R.id.calories);
        protein = findViewById(R.id.protein);
        fat = findViewById(R.id.fat);
        carbs = findViewById(R.id.carbs);

        addNut = findViewById(R.id.addNut);

        addNut.setOnClickListener(v -> {

            addNuts();

        });
    }

    public void addNuts(){
        Nutrition newNut = new Nutrition(user.getUsername(), Integer.parseInt(calories.getText().toString()),
                                                             Integer.parseInt(protein.getText().toString()),
                                                             Integer.parseInt(fat.getText().toString()),
                                                             Integer.parseInt(carbs.getText().toString()));

        GetNutritionApi().addNutrition(newNut).enqueue(new SlimCallback<Nutrition>(response -> {
            Log.d("add nut", "nut added" );
        }));
    }
}