package com.example.sanke.goals;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ArmsActivity extends AppCompatActivity {

    Vibrator v;

    public Workout workout1 = new Workout();
    public Workout workout2 = new Workout();
    public Workout workout3 = new Workout();
    public Workout workout4 = new Workout();
    public Workout workout5 = new Workout();
    public Workout workout6 = new Workout();
    public Workout workout7 = new Workout();

    private static final String TAG = "sanke.goals";
    public String bodyPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Display the Activity
        setContentView(R.layout.activity_arms);

        v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        Typeface elite = Typeface.createFromAsset(getAssets(), "SpecialElite.ttf");
        Typeface capture_it = Typeface.createFromAsset(getAssets(), "Capture_it.ttf");

        TextView t1 = (TextView)findViewById(R.id.workout1Text);
        TextView t2 = (TextView)findViewById(R.id.workout2Text);
        TextView t3 = (TextView)findViewById(R.id.workout3Text);
        TextView t4 = (TextView)findViewById(R.id.workout4Text);
        TextView t5 = (TextView)findViewById(R.id.workout5Text);
        TextView t6 = (TextView)findViewById(R.id.workout6Text);
        TextView t7 = (TextView)findViewById(R.id.workout7Text);

        Button b1 = (Button)findViewById(R.id.logButton);
        Button b2 = (Button)findViewById(R.id.mainMenuButton);

        t1.setTypeface(elite);
        t2.setTypeface(elite);
        t3.setTypeface(elite);
        t4.setTypeface(elite);
        t5.setTypeface(elite);
        t6.setTypeface(elite);
        t7.setTypeface(elite);

        b1.setTypeface(capture_it);
        b2.setTypeface(capture_it);


        // get the body part data from session activity
        Bundle bodyPartData = getIntent().getExtras();
        if(bodyPartData==null)
        {
            Log.v(TAG, "BODY PART DATA WAS NOT PASSED!");
            return;
        }
        bodyPart = bodyPartData.getString("workoutMessage");
        Log.v(TAG, "BODY PART IS " + bodyPart);

        // Call helper method to setup the workout activity depending on body part
        setupWorkoutForm(bodyPart);

        inputHandlerW1(workout1);
        inputHandlerW2(workout2);
        inputHandlerW3(workout3);
        inputHandlerW4(workout4);
        inputHandlerW5(workout5);
        inputHandlerW6(workout6);
        inputHandlerW7(workout7);
    }

    // go back to main menu
    public void onClickMainMenuButton(View view){
        v.vibrate(10);
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }

    // what happens when the workout session log button is pressed
    public void onClickLog(View view) {

        v.vibrate(50);

        new AlertDialog.Builder(this)
                .setTitle("Log Session")
                .setMessage("Are you sure you want log this session?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        logSession();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(R.mipmap.ic_launcher)
                .show();
    }

    public void logSession(){


        DatabaseHandler db = new DatabaseHandler(this);

        Log.v(TAG, "LOG BUTTON CLICKED!");

        inputHandlerW1(workout1);
        inputHandlerW2(workout2);
        inputHandlerW3(workout3);
        inputHandlerW4(workout4);
        inputHandlerW5(workout5);
        inputHandlerW6(workout6);
        inputHandlerW7(workout7);


        // Inserting Workout
        Log.d(TAG, "Inserting ..");
        db.addWorkout(workout1);
        db.addWorkout(workout2);
        db.addWorkout(workout3);
        db.addWorkout(workout4);
        db.addWorkout(workout5);
        db.addWorkout(workout6);
        db.addWorkout(workout7);

        Context context = getApplicationContext();
        CharSequence text = "Workout session " + workout1.getBodypart() + " logged!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // Reading all workouts
        Log.d(TAG, "Reading all contacts..");
        List<Workout> workouts = db.getAllWorkouts();

        for (Workout cn : workouts) {
            String log = "Id: " + cn.getID() + " ,Date: " + cn.getDate() + " ,Body-part: " + cn.getBodypart() + " ,Name: " + cn.getName() + " ,Sets: " + cn.getSets() + " ,Reps: " + cn.getReps() + " ,Weight: " + cn.getWeight();
            // Writing Workouts to log
            Log.d(TAG, log);
        }

    }

    // Helper method to setup the workout activity depending on body part
    public void setupWorkoutForm(String bodyPart){
        if (bodyPart.equals("arms")){
            final TextView w1Text = (TextView) findViewById(R.id.workout1Text);
            w1Text.setText("Chin-Up");
            final TextView w2Text = (TextView) findViewById(R.id.workout2Text);
            w2Text.setText("Pushups");
            final TextView w3Text = (TextView) findViewById(R.id.workout3Text);
            w3Text.setText("Wide-Grip Standing Barbell Curl");
            final TextView w4Text = (TextView) findViewById(R.id.workout4Text);
            w4Text.setText("Dumbbell One-Arm Triceps Extension");
            final TextView w5Text = (TextView) findViewById(R.id.workout5Text);
            w5Text.setText("Reverse Barbell Curl(EZ-Bar)");
            final TextView w6Text = (TextView) findViewById(R.id.workout6Text);
            w6Text.setText("EZ-Bar Skullcrusher");
            final TextView w7Text = (TextView) findViewById(R.id.workout7Text);
            w7Text.setText("Tricep Dumbbell Kickback");
        }
        else if (bodyPart.equals("legs")){
            final TextView w1Text = (TextView) findViewById(R.id.workout1Text);
            w1Text.setText("Dumbbell Step Ups");
            final TextView w2Text = (TextView) findViewById(R.id.workout2Text);
            w2Text.setText("Romanian Deadlift");
            final TextView w3Text = (TextView) findViewById(R.id.workout3Text);
            w3Text.setText("Leg Press");
            final TextView w4Text = (TextView) findViewById(R.id.workout4Text);
            w4Text.setText("Stiff-Legged Dumbbell Deadlift");
            final TextView w5Text = (TextView) findViewById(R.id.workout5Text);
            w5Text.setText("Alternating Dumbbell Lunges");
            final TextView w6Text = (TextView) findViewById(R.id.workout6Text);
            w6Text.setText("Standing Calf Raises");
            final TextView w7Text = (TextView) findViewById(R.id.workout7Text);
            w7Text.setText("Seated Calf Raise");
        }
        else if (bodyPart.equals("chest")){
            final TextView w1Text = (TextView) findViewById(R.id.workout1Text);
            w1Text.setText("Isometric Wipers");
            final TextView w2Text = (TextView) findViewById(R.id.workout2Text);
            w2Text.setText("Push Up to Side Plank");
            final TextView w3Text = (TextView) findViewById(R.id.workout3Text);
            w3Text.setText("Dumbbell Bench-Press");
            final TextView w4Text = (TextView) findViewById(R.id.workout4Text);
            w4Text.setText("Cable Crossover");
            final TextView w5Text = (TextView) findViewById(R.id.workout5Text);
            w5Text.setText("Dumbbell Incline Bench Press");
            final TextView w6Text = (TextView) findViewById(R.id.workout6Text);
            w6Text.setText("Dips - Chest Vesion");
            final TextView w7Text = (TextView) findViewById(R.id.workout7Text);
            w7Text.setText("Incline Dumbbell Flyes");
        }
        else if (bodyPart.equals("back")){
            final TextView w1Text = (TextView) findViewById(R.id.workout1Text);
            w1Text.setText("Inverted Row");
            final TextView w2Text = (TextView) findViewById(R.id.workout2Text);
            w2Text.setText("Pullups");
            final TextView w3Text = (TextView) findViewById(R.id.workout3Text);
            w3Text.setText("Wide-Grip Lat Pulldown");
            final TextView w4Text = (TextView) findViewById(R.id.workout4Text);
            w4Text.setText("Bent Over Two-Dummbell Row");
            final TextView w5Text = (TextView) findViewById(R.id.workout5Text);
            w5Text.setText("Bent-Arm Dumbbell Pullover");
            final TextView w6Text = (TextView) findViewById(R.id.workout6Text);
            w6Text.setText("Dumbbell Shrug");
            final TextView w7Text = (TextView) findViewById(R.id.workout7Text);
            w7Text.setText("Hyperextensions (Back Extensions)");
        }
        else if (bodyPart.equals("shoulder-abs")){
            final TextView w1Text = (TextView) findViewById(R.id.workout1Text);
            w1Text.setText("Supine Two-Arm Overhead Throw");
            final TextView w2Text = (TextView) findViewById(R.id.workout2Text);
            w2Text.setText("Plank");
            final TextView w3Text = (TextView) findViewById(R.id.workout3Text);
            w3Text.setText("Weighted Crunches");
            final TextView w4Text = (TextView) findViewById(R.id.workout4Text);
            w4Text.setText("Calf Press");
            final TextView w5Text = (TextView) findViewById(R.id.workout5Text);
            w5Text.setText("Dumbbell Shoulder Press");
            final TextView w6Text = (TextView) findViewById(R.id.workout6Text);
            w6Text.setText("Side Lateral Raise");
            final TextView w7Text = (TextView) findViewById(R.id.workout7Text);
            w7Text.setText("Seated Bent-Over Rear Delt Raise");
        }
        else
        {
            Log.v(TAG, "ERROR: CANNOT SETUP WORKOUT ACTIVITY, BODY-PART NOT DETECTED");
        }

    }

    public void inputHandlerW1(final Workout workout){

        // set the name of each workout depending on body-part (try to get it as a bundle from previous activity)
        if (bodyPart.equals("arms")){
            workout.setBodypart("arms");
            workout.setName("Chin-Up");
        }
        else if(bodyPart.equals("legs")){
            workout.setBodypart("legs");
            workout.setName("Dumbbell Step Ups");
        }
        else if(bodyPart.equals("chest")){
            workout.setBodypart("chest");
            workout.setName("Isometric Wipers");
        }
        else if(bodyPart.equals("back")){
            workout.setBodypart("back");
            workout.setName("Inverted Row");
        }
        else if(bodyPart.equals("shoulder-abs")){
            workout.setBodypart("shoulder-abs");
            workout.setName("Supine Two-Arm Overhead Throw");
        }
        else
        {
            workout.setName("ERROR: body part not detected");
        }

        workout.setDate(currentDate());

        // Spinner for workout 1
        Spinner w1_setSpinner = (Spinner) findViewById(R.id.workout1SetSpinner);
        Spinner w1_repSpinner = (Spinner) findViewById(R.id.workout1RepSpinner);
        Spinner w1_weightSpinner = (Spinner) findViewById(R.id.workout1WeightSpinner);

        String[] sets = new String[] { "1", "2", "3" , "4" };
        String[] reps = new String[] { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
        String[] weight = new String[] { "5", "7.5", "10", "12.5", "15", "17.5", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80"};



        ArrayAdapter<String> SetsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, sets);
        ArrayAdapter<String> RepsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, reps);
        ArrayAdapter<String> WeightsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, weight);

        w1_setSpinner.setAdapter(SetsAdapter);
        w1_setSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w1_repSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setSets((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w1_repSpinner.setAdapter(RepsAdapter);
        w1_repSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w1_setSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setReps((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w1_weightSpinner.setAdapter(WeightsAdapter);
        w1_weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w1_weightSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setWeight((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }

    public void inputHandlerW2(final Workout workout){

        if (bodyPart.equals("arms")){
            workout.setBodypart("arms");
            workout.setName("Pushups");
        }
        else if(bodyPart.equals("legs")){
            workout.setBodypart("legs");
            workout.setName("Romanian Deadlift");
        }
        else if(bodyPart.equals("chest")){
            workout.setBodypart("chest");
            workout.setName("Push Up to Side Plank");
        }
        else if(bodyPart.equals("back")){
            workout.setBodypart("back");
            workout.setName("Pullups");
        }
        else if(bodyPart.equals("shoulder-abs")){
            workout.setBodypart("shoulder-abs");
            workout.setName("Plank");
        }
        else{
            workout.setName("ERROR: body part not detected");
        }

        workout.setDate(currentDate());

        // Spinner for workout 1
        Spinner w2_setSpinner = (Spinner) findViewById(R.id.workout2SetSpinner);
        Spinner w2_repSpinner = (Spinner) findViewById(R.id.workout2RepSpinner);
        Spinner w2_weightSpinner = (Spinner) findViewById(R.id.workout2WeightSpinner);

        String[] sets = new String[] { "1", "2", "3" , "4" };
        String[] reps = new String[] { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
        String[] weight = new String[] { "5", "7.5", "10", "12.5", "15", "17.5", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80"};


        ArrayAdapter<String> SetsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, sets);
        ArrayAdapter<String> RepsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, reps);
        ArrayAdapter<String> WeightsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, weight);

        w2_setSpinner.setAdapter(SetsAdapter);
        w2_setSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_repSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setSets((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w2_repSpinner.setAdapter(RepsAdapter);
        w2_repSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_setSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setReps((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w2_weightSpinner.setAdapter(WeightsAdapter);
        w2_weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_weightSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setWeight((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }

    public void inputHandlerW3(final Workout workout){


        if (bodyPart.equals("arms")){
            workout.setBodypart("arms");
            workout.setName("Wide-Grip Standing Barbell Curl");
        }
        else if(bodyPart.equals("legs")){
            workout.setBodypart("legs");
            workout.setName("Leg Press");
        }
        else if(bodyPart.equals("chest")){
            workout.setBodypart("chest");
            workout.setName("Dumbbell Bench-Press");
        }
        else if(bodyPart.equals("back")){
            workout.setBodypart("back");
            workout.setName("Wide-Grip Lat Pulldown");
        }
        else if(bodyPart.equals("shoulder-abs")){
            workout.setBodypart("shoulder-abs");
            workout.setName("Weighted Crunches");
        }
        else{
            workout.setName("ERROR: body part not detected");
        }


        workout.setDate(currentDate());

        // Spinner for workout 1
        Spinner w3_setSpinner = (Spinner) findViewById(R.id.workout3SetSpinner);
        Spinner w3_repSpinner = (Spinner) findViewById(R.id.workout3RepSpinner);
        Spinner w3_weightSpinner = (Spinner) findViewById(R.id.workout3WeightSpinner);

        String[] sets = new String[] { "1", "2", "3" , "4" };
        String[] reps = new String[] { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
        String[] weight = new String[] { "5", "7.5", "10", "12.5", "15", "17.5", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80"};


        ArrayAdapter<String> SetsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, sets);
        ArrayAdapter<String> RepsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, reps);
        ArrayAdapter<String> WeightsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, weight);

        w3_setSpinner.setAdapter(SetsAdapter);
        w3_setSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_repSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setSets((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w3_repSpinner.setAdapter(RepsAdapter);
        w3_repSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_setSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setReps((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w3_weightSpinner.setAdapter(WeightsAdapter);
        w3_weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_weightSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setWeight((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }

    public void inputHandlerW4(final Workout workout){


        if (bodyPart.equals("arms")){
            workout.setBodypart("arms");
            workout.setName("Dumbbell One-Arm Triceps Extension");
        }
        else if(bodyPart.equals("legs")){
            workout.setBodypart("legs");
            workout.setName("Stiff-Legged Dumbbell Deadlift");
        }
        else if(bodyPart.equals("chest")){
            workout.setBodypart("chest");
            workout.setName("Cable Crossover");
        }
        else if(bodyPart.equals("back")){
            workout.setBodypart("back");
            workout.setName("Bent Over Two-Dummbell Row");
        }
        else if(bodyPart.equals("shoulder-abs")){
            workout.setBodypart("shoulder-abs");
            workout.setName("Calf Press");
        }
        else{
            workout.setName("ERROR: body part not detected");
        }

        workout.setDate(currentDate());

        // Spinner for workout 1
        Spinner w4_setSpinner = (Spinner) findViewById(R.id.workout4SetSpinner);
        Spinner w4_repSpinner = (Spinner) findViewById(R.id.workout4RepSpinner);
        Spinner w4_weightSpinner = (Spinner) findViewById(R.id.workout4WeightSpinner);

        String[] sets = new String[] { "1", "2", "3" , "4" };
        String[] reps = new String[] { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
        String[] weight = new String[] { "5", "7.5", "10", "12.5", "15", "17.5", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80"};


        ArrayAdapter<String> SetsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, sets);
        ArrayAdapter<String> RepsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, reps);
        ArrayAdapter<String> WeightsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, weight);

        w4_setSpinner.setAdapter(SetsAdapter);
        w4_setSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_repSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setSets((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w4_repSpinner.setAdapter(RepsAdapter);
        w4_repSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_setSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setReps((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w4_weightSpinner.setAdapter(WeightsAdapter);
        w4_weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_weightSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setWeight((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void inputHandlerW5(final Workout workout){


        if (bodyPart.equals("arms")){
            workout.setBodypart("arms");
            workout.setName("Reverse Barbell Curl(EZ-Bar)");
        }
        else if(bodyPart.equals("legs")){
            workout.setBodypart("legs");
            workout.setName("Alternating Dumbbell Lunges");
        }
        else if(bodyPart.equals("chest")){
            workout.setBodypart("chest");
            workout.setName("Dumbbell Incline Bench Press");
        }
        else if(bodyPart.equals("back")){
            workout.setBodypart("back");
            workout.setName("Bent-Arm Dumbbell Pullover");
        }
        else if(bodyPart.equals("shoulder-abs")){
            workout.setBodypart("shoulder-abs");
            workout.setName("Dumbbell Shoulder Press");
        }
        else{
            workout.setName("ERROR: body part not detected");
        }

        workout.setDate(currentDate());

        // Spinner for workout 1
        Spinner w5_setSpinner = (Spinner) findViewById(R.id.workout5SetSpinner);
        Spinner w5_repSpinner = (Spinner) findViewById(R.id.workout5RepSpinner);
        Spinner w5_weightSpinner = (Spinner) findViewById(R.id.workout5WeightSpinner);

        String[] sets = new String[] { "1", "2", "3" , "4" };
        String[] reps = new String[] { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
        String[] weight = new String[] { "5", "7.5", "10", "12.5", "15", "17.5", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80"};


        ArrayAdapter<String> SetsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, sets);
        ArrayAdapter<String> RepsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, reps);
        ArrayAdapter<String> WeightsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, weight);

        w5_setSpinner.setAdapter(SetsAdapter);
        w5_setSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_repSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setSets((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w5_repSpinner.setAdapter(RepsAdapter);
        w5_repSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_setSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setReps((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w5_weightSpinner.setAdapter(WeightsAdapter);
        w5_weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_weightSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setWeight((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void inputHandlerW6(final Workout workout){

        if (bodyPart.equals("arms")){
            workout.setBodypart("arms");
            workout.setName("EZ-Bar Skullcrusher");
        }
        else if(bodyPart.equals("legs")){
            workout.setBodypart("legs");
            workout.setName("Standing Calf Raises");
        }
        else if(bodyPart.equals("chest")){
            workout.setBodypart("chest");
            workout.setName("Dips - Chest Vesion");
        }
        else if(bodyPart.equals("back")){
            workout.setBodypart("back");
            workout.setName("Dumbbell Shrug");
        }
        else if(bodyPart.equals("shoulder-abs")){
            workout.setBodypart("shoulder-abs");
            workout.setName("Side Lateral Raise");
        }
        else{
            workout.setName("ERROR: body part not detected");
        }

        workout.setDate(currentDate());

        // Spinner for workout 1
        Spinner w6_setSpinner = (Spinner) findViewById(R.id.workout6SetSpinner);
        Spinner w6_repSpinner = (Spinner) findViewById(R.id.workout6RepSpinner);
        Spinner w6_weightSpinner = (Spinner) findViewById(R.id.workout6WeightSpinner);

        String[] sets = new String[] { "1", "2", "3" , "4" };
        String[] reps = new String[] { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
        String[] weight = new String[] { "5", "7.5", "10", "12.5", "15", "17.5", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80"};


        ArrayAdapter<String> SetsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, sets);
        ArrayAdapter<String> RepsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, reps);
        ArrayAdapter<String> WeightsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, weight);

        w6_setSpinner.setAdapter(SetsAdapter);
        w6_setSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_repSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setSets((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w6_repSpinner.setAdapter(RepsAdapter);
        w6_repSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_setSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setReps((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w6_weightSpinner.setAdapter(WeightsAdapter);
        w6_weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_weightSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setWeight((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void inputHandlerW7(final Workout workout){

        if (bodyPart.equals("arms")){
            workout.setBodypart("arms");
            workout.setName("Tricep Dumbbell Kickback");
        }
        else if(bodyPart.equals("legs")){
            workout.setBodypart("legs");
            workout.setName("Seated Calf Raise");
        }
        else if(bodyPart.equals("chest")){
            workout.setBodypart("chest");
            workout.setName("Incline Dumbbell Flyes");
        }
        else if(bodyPart.equals("back")){
            workout.setBodypart("back");
            workout.setName("Hyperextensions (Back Extensions)");
        }
        else if(bodyPart.equals("shoulder-abs")){
            workout.setBodypart("shoulder-abs");
            workout.setName("Seated Bent-Over Rear Delt Raise");
        }
        else{
            workout.setName("ERROR: body part not detected");
        }

        workout.setDate(currentDate());

        // Spinner for workout 1
        Spinner w7_setSpinner = (Spinner) findViewById(R.id.workout7SetSpinner);
        Spinner w7_repSpinner = (Spinner) findViewById(R.id.workout7RepSpinner);
        Spinner w7_weightSpinner = (Spinner) findViewById(R.id.workout7WeightSpinner);

        String[] sets = new String[] { "1", "2", "3" , "4" };
        String[] reps = new String[] { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
        String[] weight = new String[] { "5", "7.5", "10", "12.5", "15", "17.5", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80"};


        ArrayAdapter<String> SetsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, sets);
        ArrayAdapter<String> RepsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, reps);
        ArrayAdapter<String> WeightsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, weight);

        w7_setSpinner.setAdapter(SetsAdapter);
        w7_setSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_repSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setSets((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w7_repSpinner.setAdapter(RepsAdapter);
        w7_repSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_setSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setReps((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        w7_weightSpinner.setAdapter(WeightsAdapter);
        w7_weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.v(TAG, "w2_weightSpinner -->" + (String) parent.getItemAtPosition(position));
                workout.setWeight((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    // retrieves the current date dynamically
    public String currentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_arms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
