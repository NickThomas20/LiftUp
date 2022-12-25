package com.example.liftup;

import static com.example.liftup.api.ApiClientFactory.GetAdminApi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.model.User;

import java.util.List;

public class UsersListActivity extends AppCompatActivity {

    private TableLayout _tableLayout;

    private boolean _loadTrainers = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        _tableLayout = findViewById(R.id.userList_tableLayout_list);

        Button usersButton = findViewById(R.id.userList_button_users);
        Button trainersButton = findViewById(R.id.userList_button_trainers);

        usersButton.setOnClickListener(v ->{
            loadUsers();
        });

        trainersButton.setOnClickListener(v ->{
            loadTrainers();
        });
    }

    private void loadUsers()
    {
        _loadTrainers = false;

loadData();
    }

    private void loadTrainers()
    {
        _loadTrainers = true;

        loadData();
    }

    private void loadData()
    {
        _tableLayout.removeAllViews();
        _tableLayout.invalidate();

        if(_loadTrainers)
        {
            GetAdminApi().getAllTrainers().enqueue(new SlimCallback<List<User>>(response -> {
                // populate layout

                Log.d("TrainerAppList", "Loaded applications: " + Integer.toString(response.size()));

                for (User user : response)
                {
                    TableRow row = new TableRow(this);

                    TextView usernameTextView = createTextView(user.getUsername());
                    Button promoteButton = createButton(_loadTrainers ? "Demote" : "Promote");
                    Button deleteButton = createButton("Delete");

                    promoteButton.setOnClickListener(v ->
                    {
                        GetAdminApi().swapTrainerStatus(user.getUsername()).enqueue(new SlimCallback<User>(acceptResponse -> {
                            loadData();
                        }));
                    });
                    deleteButton.setOnClickListener(v ->
                    {
                        GetAdminApi().deleteSpecificUser(user.getUsername()).enqueue(new SlimCallback<Validation>(acceptResponse -> {
                            loadData();
                        }));
                    });

                    row.addView(usernameTextView, new TableRow.LayoutParams(1));
                    row.addView(promoteButton, new TableRow.LayoutParams(2));
                    row.addView(deleteButton, new TableRow.LayoutParams(3));

                    _tableLayout.addView(row, new TableLayout.LayoutParams());
                }
            }, "AdminApi"));
        } else {
            GetAdminApi().getNonTrainers().enqueue(new SlimCallback<List<User>>(response -> {
                // populate layout

                Log.d("TrainerAppList", "Loaded applications: " + Integer.toString(response.size()));

                for (User user : response)
                {
                    TableRow row = new TableRow(this);

                    TextView usernameTextView = createTextView(user.getUsername());
                    Button promoteButton = createButton(_loadTrainers ? "Demote" : "Promote");
                    Button deleteButton = createButton("Delete");

                    promoteButton.setOnClickListener(v ->
                    {
                        GetAdminApi().swapTrainerStatus(user.getUsername()).enqueue(new SlimCallback<User>(acceptResponse -> {
                            loadData();
                        }));
                    });
                    deleteButton.setOnClickListener(v ->
                    {
                        GetAdminApi().deleteSpecificUser(user.getUsername()).enqueue(new SlimCallback<Validation>(acceptResponse -> {
                            loadData();
                        }));
                    });

                    row.addView(usernameTextView, new TableRow.LayoutParams(1));
                    row.addView(promoteButton, new TableRow.LayoutParams(2));
                    row.addView(deleteButton, new TableRow.LayoutParams(3));

                    _tableLayout.addView(row, new TableLayout.LayoutParams());
                }
            }, "AdminApi"));
        }
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