package com.example.liftup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.liftup.api.SlimCallback;
import com.example.liftup.api.WorkoutInstanceApi;
import com.example.liftup.model.DateBase;
import com.example.liftup.model.NutritionDate;
import com.example.liftup.model.WorkoutDate;
import com.example.liftup.model.WorkoutInstance;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.OnNavigationButtonClickedListener;
import org.naishadhparmar.zcustomcalendar.Property;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.liftup.api.ApiClientFactory .*;

// example for use here
// https://github.com/roomorama/Caldroid/blob/master/caldroidSampleActivity/src/main/java/com/caldroidsample/CaldroidSampleActivity.java

public class CalendarActivity extends AppCompatActivity implements OnNavigationButtonClickedListener {

    private static final String DATE_DEFAULT = "default";
    private static final String DATE_CURRENT = "current";
    private static final String DATE_GOOD = "good";
    private static final String DATE_BAD = "bad";

    private CustomCalendar _calendar;

    private List<WorkoutDate> _loadedWorkDates;
    private List<NutritionDate> _loadedNutDates;
    private List<WorkoutInstance> _loadedInstances;

    private Button _addButton;
    private Button _removeButton;
    private Button _completeButton;

    private Button _addButton2;
    private Button _removeButton2;

    private EditText _workoutIdEditText;
    private EditText _liftIdEditText;
    private EditText _setsEditText;
    private EditText _repsEditText;
    private EditText _caloriesEditText;
    private EditText _proteinEditText;
    private EditText _fatEditText;
    private EditText _carbsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initializeCalendar();

        loadDates(true);

        _addButton = findViewById(R.id.calendar_button_add);
        _removeButton = findViewById(R.id.calendar_button_remove);
        _addButton2 = findViewById(R.id.calendar_button_add2);
        _removeButton2 = findViewById(R.id.calendar_button_remove2);
        _completeButton = findViewById(R.id.calendar_button_complete);

        _workoutIdEditText = findViewById(R.id.calendar_editTextNumber_w0);
        _liftIdEditText = findViewById(R.id.calendar_editTextNumber_w1);
        _setsEditText = findViewById(R.id.calendar_editTextNumber_w2);
        _repsEditText = findViewById(R.id.calendar_editTextNumber_w3);
        _setsEditText.setClickable(false);
        _repsEditText.setClickable(false);

        _caloriesEditText = findViewById(R.id.calendar_editTextNumber_nut0);
        _proteinEditText = findViewById(R.id.calendar_editTextNumber_nut1);
        _fatEditText = findViewById(R.id.calendar_editTextNumber_nut2);
        _carbsEditText = findViewById(R.id.calendar_editTextNumber_nut3);

        _workoutIdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // get based on lift and workout
                GetWorkoutInstanceApi().allWorkoutsInstances(getSelectedWorkoutId()).enqueue(new SlimCallback<List<WorkoutInstance>>(response ->{
                    _loadedInstances = response;

                    updateLiftData();
                }));
            }
        });

        _liftIdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateLiftData();
            }
        });

        _addButton.setOnClickListener(v -> {
            Calendar selected = _calendar.getSelectedDate();

            // if date exists, edit
            // otherwise add
            WorkoutDate date = findDate(selected.getTime().toString(), _loadedWorkDates);

            // get new values
            int workoutId = Integer.parseInt(_workoutIdEditText.getText().toString());

            // set to new date if needed
            if(date == null)
            {
                date = new WorkoutDate(Session.getUsername(), selected.getTime(), workoutId, false);
            } else
            {
                // just update
                date.setWorkoutId(workoutId);
            }

            GetCalendarApi().addUserDate(date).enqueue(new SlimCallback<WorkoutDate>(response -> {
                loadDates(true);
            }, "CalendarApi"));
        });

        _removeButton.setOnClickListener(v -> {
            Calendar selected = _calendar.getSelectedDate();

            Log.d("Delete Calendar", "Deleting: " + selected.getTime().toString());

            // find workout date
            WorkoutDate date = findDate(selected.getTime().toString(), _loadedWorkDates);

            GetCalendarApi().deleteUserDate(date.getUsername(), date.getDate()).enqueue(new SlimCallback<Validation>(response -> {
                if(response.isValid())
                {
                    loadDates(true);
                }
            }, "CalendarApi"));
        });

        _addButton2.setOnClickListener(v -> {
            Calendar selected = _calendar.getSelectedDate();

            // if date exists, edit
            // otherwise add
            NutritionDate date = (NutritionDate) findDate(selected.getTime().toString(), (List<DateBase>)(Object)_loadedNutDates);

            // get new values
            int calories = Integer.parseInt(((EditText)findViewById(R.id.calendar_editTextNumber_nut0)).getText().toString());
            int protein = Integer.parseInt(((EditText)findViewById(R.id.calendar_editTextNumber_nut1)).getText().toString());
            int fat = Integer.parseInt(((EditText)findViewById(R.id.calendar_editTextNumber_nut2)).getText().toString());
            int carbs = Integer.parseInt(((EditText)findViewById(R.id.calendar_editTextNumber_nut3)).getText().toString());

            // set to new date if needed
            if(date == null)
            {
                date = new NutritionDate(Session.getUsername(), selected.getTime(), calories, protein, fat, carbs);
            } else
            {
                // just update
                date.setCalories(calories);
                date.setProtein(protein);
                date.setFat(fat);
                date.setCarbs(carbs);
            }

            GetNutritionDateApi().addUserNutritionDate(date).enqueue(new SlimCallback<NutritionDate>(response -> {
                loadDates(true);
            }, "CalendarApi"));
        });

        _removeButton2.setOnClickListener(v -> {
            Calendar selected = _calendar.getSelectedDate();

            Log.d("Delete Calendar", "Deleting2: " + selected.getTime().toString());

            // find workout date
            NutritionDate date = (NutritionDate) findDate(selected.getTime().toString(), (List<DateBase>)(Object)_loadedNutDates);

            GetNutritionDateApi().deleteUserNutrition(date.getUsername(), date.getDate()).enqueue(new SlimCallback<Validation>(response -> {
                if(response.isValid())
                {
                    loadDates(true);
                }
            }, "CalendarApi"));
        });

        _completeButton.setOnClickListener(v -> {
            Calendar selected = _calendar.getSelectedDate();

            // if date exists, edit
            // otherwise add
            WorkoutDate date = (WorkoutDate)findDate(selected.getTime(), (List<DateBase>)(Object)_loadedWorkDates);

            // set to new date if needed
            if(date == null) {
                return;
            }

            // get new values
            int workoutId = Integer.parseInt(_workoutIdEditText.getText().toString());

            date.setWorkoutId(workoutId);
            date.setCompleted(true);

            GetCalendarApi().addUserDate(date).enqueue(new SlimCallback<WorkoutDate>(response -> {
                loadDates(true);
            }, "CalendarApi"));
        });
    }

    long getSelectedId(EditText et)
    {
        String text = et.getText().toString();
        return Utility.isStringEmptyOrWhitespace(text) ? 0l : Long.parseLong(text);
    }

    long getSelectedWorkoutId()
    {
        return getSelectedId(_workoutIdEditText);
    }

    long getSelectedLiftId()
    {
        return getSelectedId(_liftIdEditText);
    }

    void updateLiftData()
    {
        long wid = getSelectedWorkoutId();
        long lid = getSelectedLiftId();

        // load based on that
        for(WorkoutInstance instance : _loadedInstances)
        {
            if(instance.getWorkoutId() == wid && instance.getLiftId() == lid)
            {
                _setsEditText.setText(Integer.toString(instance.getSets()));
                _repsEditText.setText(Integer.toString(instance.getReps()));
                return;
            }
        }

        // could not find, set to default (0 and 0)
        _setsEditText.setText("0");
        _repsEditText.setText("0");
    }

    private void updateButtons(Date selectedDate)
    {
        Calendar today = getToday();

        // if there is a workout on this date...
        WorkoutDate w = (WorkoutDate) findDate(selectedDate.toString(), (List<DateBase>)(Object)_loadedWorkDates);
        NutritionDate n = (NutritionDate) findDate(selectedDate.toString(), (List<DateBase>)(Object)_loadedNutDates);

        if(w == null)
        {
            _workoutIdEditText.setText(Integer.toString(0));
        } else {
            _workoutIdEditText.setText(Integer.toString(w.getWorkoutId()));
        }

        if(n == null)
        {
            _caloriesEditText.setText("0");
            _proteinEditText.setText("0");
            _fatEditText.setText("0");
            _carbsEditText.setText("0");
        } else {
            _caloriesEditText.setText(Integer.toString(n.getCalories()));
            _proteinEditText.setText(Integer.toString(n.getProtein()));
            _fatEditText.setText(Integer.toString(n.getFat()));
            _carbsEditText.setText(Integer.toString(n.getCarbs()));
        }

        // complete only if today
        setButtonInteractive(_completeButton, compareDates(selectedDate, today.getTime()) == 0 && w != null && !w.isCompleted());

        // remove only if in the future
        setButtonInteractive(_removeButton, compareDates(selectedDate, today.getTime()) >= 0 && w != null);

        // add only if in the future
        setButtonInteractive(_addButton, compareDates(selectedDate, today.getTime()) >= 0);
        _addButton.setText(w != null ? "Update" : "Add"); // update, if there is a date already there

        // remove only if in the future
        setButtonInteractive(_removeButton2, compareDates(selectedDate, today.getTime()) >= 0 && n != null);

        // add only if in the future
        setButtonInteractive(_addButton2, compareDates(selectedDate, today.getTime()) >= 0);
        _addButton2.setText(n != null ? "Update" : "Add"); // update, if there is a date already there
    }

    private void setButtonInteractive(Button button, boolean interactive)
    {
        button.setClickable(interactive);
        button.setAlpha(interactive ? 1.0f : 0.5f);
    }

    private <T> T findDate(String date, List<T> list)
    {
        return (T)findDate(Utility.parseDate(date), (List<DateBase>)(Object)list);
    }

    private DateBase findDate(Date date, List<DateBase> list)
    {
        for(DateBase d : list)
        {
            if(Utility.compareDates(d.getDateAsDate(), date) == 0)
            {
                return d;
            }
        }

        return null;
    }

    private void initializeCalendar()
    {
        _calendar = findViewById(R.id.calendar_customCalendar_calendar);

        // set up map properties
        HashMap<Object, Property> mapDescToProp = new HashMap<>();

        // default
        Property prop = new Property();
        prop.layoutResource = R.layout.calendar_default_view;
        prop.dateTextViewResource = R.id.default_datetextview;
        mapDescToProp.put(DATE_DEFAULT, prop);

        // current date
        prop = new Property();
        prop.layoutResource = R.layout.calendar_current_view;
        prop.dateTextViewResource = R.id.current_datetextview;
        mapDescToProp.put(DATE_CURRENT, prop);

        // good date
        prop = new Property();
        prop.layoutResource = R.layout.calendar_good_view;
        prop.dateTextViewResource = R.id.good_datetextview;
        mapDescToProp.put(DATE_GOOD, prop);

        // bad date
        prop = new Property();
        prop.layoutResource = R.layout.calendar_bad_view;
        prop.dateTextViewResource = R.id.bad_datetextview;
        mapDescToProp.put(DATE_BAD, prop);

        _calendar.setMapDescToProp(mapDescToProp);

        _calendar.setOnNavigationButtonClickedListener(CustomCalendar.PREVIOUS, this);
        _calendar.setOnNavigationButtonClickedListener(CustomCalendar.NEXT, this);

        _calendar.setOnDateSelectedListener((v, c, o) -> {
            Log.d("CalendarActivity", "Selected: " + c.getTime().toString());

            updateButtons(c.getTime());
        });
    }

    private void loadDates(boolean updateCalendar){
        // clear old dates
        _loadedWorkDates = null;
        _loadedNutDates = null;

        // load dates

        GetCalendarApi().viewSpecificUsersDates(Session.getUsername()).enqueue(new SlimCallback<List<WorkoutDate>>(response -> {
            _loadedWorkDates = response;

            if(updateCalendar && _loadedNutDates != null)
            {
                // get selected
                Date selectedDate = _calendar.getSelectedDate().getTime();

                // update
                updateCalendar();

                // update buttons
                updateButtons(selectedDate);
            }
        }, "CalendarApi"));

        GetNutritionDateApi().viewSpecificUsersNutrition(Session.getUsername()).enqueue(new SlimCallback<List<NutritionDate>>(response -> {
            _loadedNutDates = response;

            if(updateCalendar && _loadedWorkDates != null)
            {
                // get selected
                Date selectedDate = _calendar.getSelectedDate().getTime();

                // update
                updateCalendar();

                // update buttons
                updateButtons(selectedDate);
            }
        }));
    }

    /**
     * Updates the Calendar with the list of dates.
     */
    private void updateCalendar(){
        Calendar month = _calendar.getSelectedDate();

        // now display calendar
        _calendar.setDate(month, getCalendarDates(month));
    }

    private List<DateBase> getDatesFromMonth(Calendar month){
        List<DateBase> dates = new ArrayList<>();

        DateBase d;

        for(int i = 0; i < _loadedWorkDates.size(); i++)
        {
            d = _loadedWorkDates.get(i);

            if(isSameMonth(month, d.getDateAsDate()))
            {
                dates.add(d);
            }
        }

        for(int i = 0; i < _loadedNutDates.size(); i++)
        {
            d = _loadedNutDates.get(i);

            if(isSameMonth(month, d.getDateAsDate()) && !dates.contains(d))
            {
                dates.add(d);
            }
        }

        return dates;
    }

    private Calendar getToday(){
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        return today;
    }

    private int compareDates(Date left, Date right)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        return format.format(left).compareTo(format.format(right));
    }

    private boolean isSameMonth(Calendar left, Calendar right)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        // same month if within the same year and the same month
        return format.format(left.getTime()).compareTo(format.format(right.getTime())) == 0;
    }

    private boolean isSameMonth(Calendar left, Date right)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(right);

        return isSameMonth(left, c);
    }

    private Map<Integer, Object> getCalendarDates(Calendar month)
    {
        Calendar today = getToday();
        Date todayDate = new Date();

        Map<Integer, Object> map = new HashMap<>();

        // get dates for this month
        List<DateBase> dates = getDatesFromMonth(month);

        Calendar other = Calendar.getInstance();

        int otherNum;

        // add to the map
        for(DateBase d : dates)
        {
            other.setTime(d.getDateAsDate());
            otherNum = other.get(Calendar.DAY_OF_MONTH);

            // decide the date color
            switch (compareDates(d.getDateAsDate(), todayDate))
            {
                case -1: // past
                    map.put(otherNum, d.isCompleted() ? DATE_GOOD : DATE_BAD);
                    break;
                case 0: // today
                    map.put(otherNum, d.isCompleted() ? DATE_GOOD : DATE_CURRENT);
                    break;
                case 1: // future
                    map.put(otherNum, DATE_CURRENT);
                    break;
            }
        }

        return map;
    }

    @Override
    public Map<Integer, Object>[] onNavigationButtonClicked(int whichButton, Calendar newMonth) {
        Map<Integer, Object>[] arr = new Map[2];
        arr[0] = getCalendarDates(newMonth);
        return arr;
    }
}