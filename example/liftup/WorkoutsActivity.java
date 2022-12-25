package com.example.liftup;

import static com.example.liftup.api.ApiClientFactory.GetUserApi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.User;
import com.example.liftup.model.Workout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.liftup.api.ApiClientFactory .*;

public class WorkoutsActivity extends AppCompatActivity {

    private static final int HEIGHT = 120;
    private static final float FONT_SIZE = 20.0f;

    private LinearLayout _workoutsList;

    private List<Workout> _workouts;

    private ArrayList<TextView> _views = new ArrayList<>();

    private Button _addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        _workoutsList = findViewById(R.id.workouts_linearLayout_list);

        updateWorkoutList();

        _addButton = findViewById(R.id.workouts_button_new);
        _addButton.setOnClickListener(v -> {
            // add new
            Workout newWorkout = new Workout();

            // set to current user and date
            newWorkout.setAuthor(Session.getUser().getUsername());
            newWorkout.setDate(new Date().toString());

            Log.d("Workouts Activity", newWorkout.toString());

            GetWorkoutApi().addWorkout(newWorkout).enqueue(new SlimCallback<Validation>(response -> {
                if(!response.isValid())
                {
                    Log.d("Workouts Activity", "Did not add workout.");
                    return;
                }

                // update view
                updateWorkoutList();

            }, "Workouts Activity"));
        });
    }

    private void updateWorkoutList(){
        GetWorkoutApi().getAllWorkouts().enqueue(new SlimCallback<List<Workout>>(response -> {
            _workouts = response;

            // add new stuff if needed
            for(int i = _views.size(); i < _workouts.size(); i++)
            {
                // create text view
                TextView view = new TextView(_workoutsList.getContext());

                // add to layout
                _workoutsList.addView(view);

                //get params
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)view.getLayoutParams();

                //set values
                params.height = HEIGHT;

                //set other view values
                view.setTextSize(FONT_SIZE);

                //update params in view
                view.setLayoutParams(params);

                // add to list of views
                _views.add(view);
            }

            // assign
            for(int i = 0; i < _views.size(); i++)
            {
                if(i < _workouts.size())
                {
                    // workout
                    _views.get(i).setText(_workouts.get(i).toString());
                } else {
                    // empty
                    _views.get(i).setText("");
                }
            }
        }, "Workouts Activity"));
    }
}