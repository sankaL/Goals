package com.example.sanke.goals;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class LogMonthActivity extends AppCompatActivity {

    private static final String TAG = "sanke.goals";
    ArrayList<Workout> workoutList = new ArrayList<Workout>();
    ArrayList<Cardio> cardioList = new ArrayList<Cardio>();
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_month);

        v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        Typeface captureit = Typeface.createFromAsset(getAssets(), "Capture_it.ttf");
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "Sportrop.otf");

        TextView heading = (TextView)findViewById(R.id.headingMonthsText);
        heading.setTypeface(typeface2);

        Button b1 = (Button)findViewById(R.id.janButton);
        Button b2 = (Button)findViewById(R.id.febButton);
        Button b3 = (Button)findViewById(R.id.marchButton);
        Button b4 = (Button)findViewById(R.id.aprilButton);
        Button b5 = (Button)findViewById(R.id.mayButton);
        Button b6 = (Button)findViewById(R.id.juneButton);
        Button b7 = (Button)findViewById(R.id.julyButton);
        Button b8 = (Button)findViewById(R.id.augButton);
        Button b9 = (Button)findViewById(R.id.sepButton);
        Button b10 = (Button)findViewById(R.id.octButton);
        Button b11 = (Button)findViewById(R.id.novButton);
        Button b12 = (Button)findViewById(R.id.decButton);


        b1.setTypeface(captureit);
        b2.setTypeface(captureit);
        b3.setTypeface(captureit);
        b4.setTypeface(captureit);
        b5.setTypeface(captureit);
        b6.setTypeface(captureit);
        b7.setTypeface(captureit);
        b8.setTypeface(captureit);
        b9.setTypeface(captureit);
        b10.setTypeface(captureit);
        b11.setTypeface(captureit);
        b12.setTypeface(captureit);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_month, menu);
        return true;
    }

    public void onClickJanuary(View view){

        v.vibrate(10);
        DatabaseHandler db = new DatabaseHandler(this);

        Bundle yearData = getIntent().getExtras();
        if(yearData==null){
            return;
        }
        String year = yearData.getString("yearMessage");

        String retrieveWorkoutMonth = year + "01";

        // Reading all workouts on the specific month
        Log.d(TAG, "Selected workouts on month: " + retrieveWorkoutMonth);
        workoutList = db.getWorkoutsUsingMonth(retrieveWorkoutMonth);


        Intent intent = new Intent(getApplicationContext(),WeightCardioPicker.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("MonthDataWeights", workoutList);
        intent.putExtras(bundle);
        startActivity(intent);


    }

    public void onClickFebruary(View view){

        v.vibrate(10);
        DatabaseHandler db = new DatabaseHandler(this);

        Bundle yearData = getIntent().getExtras();
        if(yearData==null){
            return;
        }
        String year = yearData.getString("yearMessage");

        String retrieveWorkoutMonth = year + "02";

        // Reading all workouts on the specific month
        Log.d(TAG, "Selected workouts on month: " + retrieveWorkoutMonth);
        workoutList = db.getWorkoutsUsingMonth(retrieveWorkoutMonth);


        Intent intent = new Intent(getApplicationContext(),WeightCardioPicker.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("MonthDataWeights", workoutList);
        intent.putExtras(bundle);
        startActivity(intent);


    }

    public void onClickMarch(View view){

        v.vibrate(10);
        DatabaseHandler db = new DatabaseHandler(this);

        Bundle yearData = getIntent().getExtras();
        if(yearData==null){
            return;
        }
        String year = yearData.getString("yearMessage");

        String retrieveWorkoutMonth = year + "03";

        // Reading all workouts on the specific month
        Log.d(TAG, "Selected workouts on month: " + retrieveWorkoutMonth);
        workoutList = db.getWorkoutsUsingMonth(retrieveWorkoutMonth);


        Intent intent = new Intent(getApplicationContext(),WeightCardioPicker.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("MonthDataWeights", workoutList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClickApril(View view){
        v.vibrate(10);
        DatabaseHandler db = new DatabaseHandler(this);

        Bundle yearData = getIntent().getExtras();
        if(yearData==null){
            return;
        }
        String year = yearData.getString("yearMessage");

        String retrieveWorkoutMonth = year + "04";

        // Reading all workouts on the specific month
        Log.d(TAG, "Seleted workouts on month: " + retrieveWorkoutMonth);
        workoutList = db.getWorkoutsUsingMonth(retrieveWorkoutMonth);


        Intent intent = new Intent(getApplicationContext(),MonthlyViewWeightsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("MonthData", workoutList);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void onClickMay(View view){

        v.vibrate(10);
        DatabaseHandler db = new DatabaseHandler(this);

        Bundle yearData = getIntent().getExtras();
        if(yearData==null){
            return;
        }
        String year = yearData.getString("yearMessage");

        String retrieveWorkoutMonth = year + "05";

        // Reading all workouts on the specific month
        Log.d(TAG, "Selected workouts on month: " + retrieveWorkoutMonth);
        workoutList = db.getWorkoutsUsingMonth(retrieveWorkoutMonth);


        Intent intent = new Intent(getApplicationContext(),WeightCardioPicker.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("MonthDataWeights", workoutList);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void onClickJune(View view){

        v.vibrate(10);
        DatabaseHandler db = new DatabaseHandler(this);

        Bundle yearData = getIntent().getExtras();
        if(yearData==null){
            return;
        }
        String year = yearData.getString("yearMessage");

        String retrieveWorkoutMonth = year + "06";

        // Reading all workouts on the specific month
        Log.d(TAG, "Selected workouts on month: " + retrieveWorkoutMonth);
        workoutList = db.getWorkoutsUsingMonth(retrieveWorkoutMonth);


        Intent intent = new Intent(getApplicationContext(),WeightCardioPicker.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("MonthDataWeights", workoutList);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void onClickJuly(View view){

        v.vibrate(10);
        DatabaseHandler db = new DatabaseHandler(this);

        Bundle yearData = getIntent().getExtras();
        if(yearData==null){
            return;
        }
        String year = yearData.getString("yearMessage");

        String retrieveWorkoutMonth = year + "07";

        // Reading all workouts on the specific month
        Log.d(TAG, "Selected workouts on month: " + retrieveWorkoutMonth);
        workoutList = db.getWorkoutsUsingMonth(retrieveWorkoutMonth);


        Intent intent = new Intent(getApplicationContext(),WeightCardioPicker.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("MonthDataWeights", workoutList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClickAugust(View view){

        v.vibrate(10);
        DatabaseHandler db = new DatabaseHandler(this);

        Bundle yearData = getIntent().getExtras();
        if(yearData==null){
            return;
        }
        String year = yearData.getString("yearMessage");

        String retrieveWorkoutMonth = year + "08";

        // Reading all workouts on the specific month
        Log.d(TAG, "Selected workouts on month: " + retrieveWorkoutMonth);
        workoutList = db.getWorkoutsUsingMonth(retrieveWorkoutMonth);


        Intent intent = new Intent(getApplicationContext(),WeightCardioPicker.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("MonthDataWeights", workoutList);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void onClickSeptember(View view){

        v.vibrate(10);
        DatabaseHandler db = new DatabaseHandler(this);

        Bundle yearData = getIntent().getExtras();
        if(yearData==null){
            return;
        }
        String year = yearData.getString("yearMessage");

        String retrieveWorkoutMonth = year + "09";

        // Reading all workouts on the specific month
        Log.d(TAG, "Selected workouts on month: " + retrieveWorkoutMonth);
        workoutList = db.getWorkoutsUsingMonth(retrieveWorkoutMonth);
        cardioList = db.getCardiosUsingMonth(retrieveWorkoutMonth);

        for (Cardio cn : cardioList) {
            String log = "Id: " + cn.get_id() + ", Date: " + cn.get_date() + ", Time: " + cn.get_time() + ", Name: " + cn.get_name() + ", Duration: " + cn.get_duration() + ", Distance: " + cn.get_distance() + ", Level: " + cn.get_level();
            // Writing Workouts to log
            Log.d(TAG, log);
        }

        Intent intent = new Intent(getApplicationContext(),WeightCardioPicker.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("MonthDataWeights", workoutList);
        bundle.putParcelableArrayList("MonthDataCardio", cardioList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClickOctober(View view){

        v.vibrate(10);
        DatabaseHandler db = new DatabaseHandler(this);

        Bundle yearData = getIntent().getExtras();
        if(yearData==null){
            return;
        }
        String year = yearData.getString("yearMessage");

        String retrieveWorkoutMonth = year + "10";

        // Reading all workouts on the specific month
        Log.d(TAG, "Selected workouts on month: " + retrieveWorkoutMonth);
        workoutList = db.getWorkoutsUsingMonth(retrieveWorkoutMonth);


        Intent intent = new Intent(getApplicationContext(),WeightCardioPicker.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("MonthDataWeights", workoutList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClickNovember(View view){

        v.vibrate(10);
        DatabaseHandler db = new DatabaseHandler(this);

        Bundle yearData = getIntent().getExtras();
        if(yearData==null){
            return;
        }
        String year = yearData.getString("yearMessage");

        String retrieveWorkoutMonth = year + "11";

        // Reading all workouts on the specific month
        Log.d(TAG, "Selected workouts on month: " + retrieveWorkoutMonth);
        workoutList = db.getWorkoutsUsingMonth(retrieveWorkoutMonth);


        Intent intent = new Intent(getApplicationContext(),WeightCardioPicker.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("MonthDataWeights", workoutList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onClickDecember(View view){

        v.vibrate(10);
        DatabaseHandler db = new DatabaseHandler(this);

        Bundle yearData = getIntent().getExtras();
        if(yearData==null){
            return;
        }
        String year = yearData.getString("yearMessage");

        String retrieveWorkoutMonth = year + "12";

        // Reading all workouts on the specific month
        Log.d(TAG, "Selected workouts on month: " + retrieveWorkoutMonth);
        workoutList = db.getWorkoutsUsingMonth(retrieveWorkoutMonth);
        cardioList = db.getCardiosUsingMonth(retrieveWorkoutMonth);


        Intent intent = new Intent(getApplicationContext(),WeightCardioPicker.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("MonthDataWeights", workoutList);
        intent.putExtras(bundle);
        startActivity(intent);

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
