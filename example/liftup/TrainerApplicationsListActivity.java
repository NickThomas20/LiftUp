package com.example.liftup;

import static com.example.liftup.api.ApiClientFactory.GetAdminApi;
import static com.example.liftup.api.ApiClientFactory.GetTrainerApplicationApi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.TrainerApplication;

import java.util.ArrayList;
import java.util.List;

public class TrainerApplicationsListActivity extends AppCompatActivity {

    TableLayout _tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_applications_list);

        _tableLayout = findViewById(R.id.trainerAppList_tableLayout_apps);

        Button backButton = findViewById(R.id.trainerAppList_button_back);
        backButton.setOnClickListener(v -> {
            ActivityLoader.loadActivity(this, ProfileActivity.class);
        });

        loadData();
    }

    private void loadData()
    {
        _tableLayout.removeAllViews();
        _tableLayout.invalidate();

        GetAdminApi().getAllTrainerApps().enqueue(new SlimCallback<List<TrainerApplication>>(response -> {
            // populate layout

            Log.d("TrainerAppList", "Loaded applications: " + Integer.toString(response.size()));

            for (TrainerApplication app : response)
            {
                TableRow row = new TableRow(this);



                TextView usernameTextView = createTextView(app.getUsername());
                TextView yearsTextView = createTextView(Integer.toString(app.getYearsActive()));
                TextView specialtyTextView = createTextView(app.getSpeciality());
                Button acceptButton = createButton("Accept");
                Button denyButton = createButton("Deny");

                acceptButton.setOnClickListener(v ->
                {
                    GetAdminApi().acceptTrainerApp(app.getUsername()).enqueue(new SlimCallback<Validation>(acceptResponse -> {
                        if(acceptResponse.isValid())
                        {
                            loadData();
                        }
                    }));
                });
                denyButton.setOnClickListener(v ->
                {
                    GetAdminApi().deleteSpecificTrainerApp(app.getUsername()).enqueue(new SlimCallback<Validation>(acceptResponse -> {
                        if(acceptResponse.isValid())
                        {
                            loadData();
                        }
                    }));
                });

                row.addView(usernameTextView, new TableRow.LayoutParams(1));
                row.addView(yearsTextView, new TableRow.LayoutParams(2));
                row.addView(specialtyTextView, new TableRow.LayoutParams(3));
                row.addView(acceptButton, new TableRow.LayoutParams(4));
                row.addView(denyButton, new TableRow.LayoutParams(5));

                _tableLayout.addView(row, new TableLayout.LayoutParams());
            }
        }, "CalendarApi"));
    }

    private TextView createTextView(String text)
    {
        TextView textView = new TextView(getApplicationContext());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        textView.setText(text);
        textView.setLayoutParams(lp);
        textView.setWidth(100);

        return textView;
    }

    private Button createButton(String text)
    {
        Button button = new Button(getApplicationContext());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        button.setLayoutParams(lp);
        button.setText(text);

        return button;
    }
}