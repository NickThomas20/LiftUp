package com.example.liftup;

import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liftup.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity {

    private TextView _nameText;
    private TextView _usernameText;
    private TextView _emailText;
    private TextView _passwordText;
    private Button _EditInfo;
    private Button nutbutton;
    private Button _StatsInfo;
    private Button liftbutton;
    private Button _trainerAppButton;
    private BottomNavigationView _Nav;
    private Button addLift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button calendarButton = findViewById(R.id.profile_button_calendar);
        calendarButton.setOnClickListener(v -> {
            ActivityLoader.loadActivity(this, "CalendarActivity");
        });

        Button workoutsButton = findViewById(R.id.profile_button_workouts);
        workoutsButton.setOnClickListener(v -> {
            ActivityLoader.loadActivity(this, "WorkoutsActivity");
        });

        _Nav = findViewById(R.id._bottomnav);

        _Nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ProfileActivity:
                        return false;
                    case R.id.EditProfileActivity:
                        edit();
                        return true;
                    case R.id.SelectStatsActivity:
                        stats();
                        return true;
                }
                return false;
            }
        });


        _nameText = findViewById(R.id.textView_Name);
        _usernameText = findViewById(R.id.textView_Username);
        _emailText = findViewById(R.id.textView_email);
        _passwordText = findViewById(R.id.textView_Password);

        nutbutton = findViewById(R.id.nutbutton);
        _EditInfo = findViewById(R.id.button_profile_editInfo);
        _StatsInfo = findViewById(R.id.button_stats);
        liftbutton = findViewById(R.id.button_stats2);

        if(Session.getUser() == null)
        {
            Session.setUser(new User());
        }

//        GetPostApi().getFirstPost().enqueue(new SlimCallback<Post>(response -> {
//            String result = "ID: " + response.getId()
//                          + "\n Title: " + response.getTitle()
//                          + "\n Body " + response.getBigText();
//            _usernameText.setText(result);
//
//        }, "CustomTagForFirstApi"));
        String name = Session.getUser().getFname() + " " +  Session.getUser().getLname();
        String username = Session.getUser().getUsername();
        String email = Session.getUser().getEmail();
        String password = Session.getUser().getPassword();
        _nameText.setText(name);
        _usernameText.setText(username);
        _emailText.setText(email);
        _passwordText.setText(password);

        _EditInfo.setOnClickListener(v -> edit());
        _StatsInfo.setOnClickListener(v -> stats());

        Button workoutButton = findViewById(R.id.profile_button_workouts);
        workoutButton.setOnClickListener(v -> {
                    ActivityLoader.loadActivity(this, "WorkoutsActivity");
                });

        liftbutton = findViewById(R.id.button_stats2);
        liftbutton.setOnClickListener(v -> {
            ActivityLoader.loadActivity(this, liftsView.class);
        });
        nutbutton.setOnClickListener(v -> {
            ActivityLoader.loadActivity(this, showNutrition.class);
        });

        addLift = findViewById(R.id.addLiftss);
        _trainerAppButton = findViewById(R.id.profile_button_trainer);
        // set based on user type
        if(Session.getUser().isAdmin())
        {
            // admin
            _trainerAppButton.setVisibility(View.VISIBLE);
            addLift.setVisibility(View.VISIBLE);

            _trainerAppButton.setText("Trainer Apps");
            _trainerAppButton.setOnClickListener(v ->
            {
                ActivityLoader.loadActivity(this, TrainerApplicationsListActivity.class);
            });

            addLift.setOnClickListener(v -> {
                ActivityLoader.loadActivity(this, TrainerView.class);

            });
        } else if (Session.getUser().isTrainer())
        {
            // trainer
            _trainerAppButton.setVisibility(View.INVISIBLE);
            addLift.setVisibility(View.VISIBLE);
        } else {
            // user
            _trainerAppButton.setVisibility(VISIBLE);
            addLift.setVisibility(View.INVISIBLE);
            _trainerAppButton.setText("Trainer App");
            _trainerAppButton.setOnClickListener(v ->
            {
                ActivityLoader.loadActivity(this, TrainerApplicationActivity.class);
            });
        }

        Button usersButton = findViewById(R.id.profile_button_users);
        usersButton.setOnClickListener(v -> {
            ActivityLoader.loadActivity(this, UsersListActivity.class);
        });
    }

    public void PullProfilePhoto()
    {
        //TODO: Pull data to fill in profile photo
    }

    private void edit()
    {
        ActivityLoader.loadActivity(this, "EditProfileActivity");
    }

    private void stats()
    {
        ActivityLoader.loadActivity(this, Websocket.class);
    }



}



