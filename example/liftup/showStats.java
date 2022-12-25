package com.example.liftup;

import static com.example.liftup.api.ApiClientFactory.GetStatsApi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.Stat;
import com.example.liftup.model.User;

import java.util.List;

public class showStats extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Button createStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);
        User user = Session.getUser();
        createStat = findViewById(R.id.createStat);

        linearLayout = findViewById(R.id.linlayout);


       GetStatsApi().getUsersOnStats(user.getUsername()).enqueue(new SlimCallback<List<Stat>>(response -> {
           Log.d("test", "Call al" );
           Log.d("resposne size", String.valueOf(response.size()));
           for(int i = 0; i < response.size(); i++)
           {
               TextView stat = new TextView(showStats.this);
               stat.setText(response.get(i).getStatName() + " " + response.get(i).getValue());

               linearLayout.addView(stat);
           }

       }));

       createStat.setOnClickListener(v -> {
           ActivityLoader.loadActivity(this, addStats.class);
       });


    }
}