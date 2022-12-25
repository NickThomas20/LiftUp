package com.example.liftup;

import static com.example.liftup.api.ApiClientFactory.GetLiftsApi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SearchView;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.Lift;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class liftsView extends AppCompatActivity {

    private ArrayList<Lift> liftList;
    private RecyclerView recyclerView;
    private Button createNewLift;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifts_view);
        createNewLift = findViewById(R.id.button_createLift);

        createNewLift.setOnClickListener(v -> {
            ActivityLoader.loadActivity(this, newLift.class);
        });

        liftList = new ArrayList<>();

        GetLiftsApi().getAllLifts().enqueue(new SlimCallback<List<Lift>>(response -> {
            Log.d("test", "Call al" );

            fillList(response);
            recyclerView = findViewById(R.id.recyclerView);
            setAdapter();
        }));


    }

    private void fillList(List<Lift> response){
        for(int i = 0; i < response.size(); i++)
        {
            Lift lift = new Lift(response.get(i).getExercise(), response.get(i).getReps());
            liftList.add(lift);

        }
    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(liftList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }





}