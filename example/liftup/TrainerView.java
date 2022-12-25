package com.example.liftup;

import static com.example.liftup.api.ApiClientFactory.GetLiftsApi;
import static com.example.liftup.api.ApiClientFactory.GetWorkoutApi;
import static com.example.liftup.api.ApiClientFactory.GetWorkoutInstanceApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.Lift;
import com.example.liftup.model.Workout;
import com.example.liftup.model.WorkoutInstance;

import java.util.List;

public class TrainerView extends AppCompatActivity {

    private TextView liftList;
    private TextView workoutList;
    private TextView liftName;
    private Button addLift;
    private Button addWorkout;
    private TextView workoutID;
    private TextView liftID;
    private TextView sets;

    private TextView reps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_view);

         liftList = findViewById(R.id.LiftList);
         workoutList = findViewById(R.id.workoutList);
         liftName = findViewById(R.id.LiftName);
         addLift = findViewById(R.id.addLift);
         addWorkout = findViewById(R.id.AddWorkout);
         workoutID = findViewById(R.id.workoutID);
         liftID = findViewById(R.id.LiftID);
         sets = findViewById(R.id.Sets);
         reps = findViewById(R.id.Reps);

        GetLiftsApi().getAllLifts().enqueue(new SlimCallback<List<Lift>>(response -> {

            String s = "";

            for(int i = 0; i < response.size(); i++)
            {
                s += response.get(i).getExercise() + " ID: " +  response.get(i).getLiftID()+ "\n";
            }

            liftList.setText(s);


        }));

        GetWorkoutApi().getAllWorkouts().enqueue(new SlimCallback<List<Workout>>(response -> {
            String s = "";

            for(int i = 0; i < response.size(); i++)
            {
                s += response.get(i).getAuthor() + " ID: " + response.get(i).getWorkoutId() + "\n";
            }

            workoutList.setText(s);
        }));



        addLift.setOnClickListener(v -> {
            addLift(liftName.getText().toString());
        });

        addWorkout.setOnClickListener(v -> {
            addWorkout(Long.parseLong(workoutID.getText().toString()),
                    Long.parseLong(liftID.getText().toString()),
                    Integer.parseInt(sets.getText().toString()),
                    Integer.parseInt(reps.getText().toString()) );
        });
    }

    private void addLift(String exercise){

        Lift lift = new Lift(exercise);

        GetLiftsApi().addLifts(lift).enqueue(new SlimCallback<Lift>(response -> {
            Log.d("add lift", "lift added" );
        }));
    }

    private void addWorkout(long workoutId, long liftId, int sets, int reps) {
        WorkoutInstance workout = new WorkoutInstance(workoutId, liftId, sets, reps);

        GetWorkoutInstanceApi().addWorkoutInstance(workout).enqueue(new SlimCallback<WorkoutInstance>(response -> {
            Log.d("add workoutinstance", "workoutinstance added" );
        }));

    }
}