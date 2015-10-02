package com.example.sanke.goals;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CardioActivity extends AppCompatActivity {

    Vibrator v;
    public Cardio cardio = new Cardio();
    private static final String TAG = "sanke.goals";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio);

        v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        cardioInputHandler(cardio);
    }

    public void onClickCardioLogSession(View view){


        v.vibrate(10);

        new AlertDialog.Builder(this)
                .setTitle("Log Cardio Session")
                .setMessage("Are you sure you want log this session?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        logCardioSession();
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

    public void logCardioSession(){

        DatabaseHandler db = new DatabaseHandler(this);

        Log.v(TAG, "LOG BUTTON CLICKED!");

        cardioInputHandler(cardio);

        // Inserting Workout
        Log.d(TAG, "Inserting ..");
        db.addCardio(cardio);

        Context context = getApplicationContext();
        CharSequence text = "Cardio session " + cardio.get_name() + " logged!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // Reading all workouts
        Log.d(TAG, "Reading all cardio sessions....");
        List<Cardio> cardios = db.getAllCardio();

        for (Cardio cn : cardios) {
            String log = "Id: " + cn.get_id() + ", Date: " + cn.get_date() + ", Time: " + cn.get_time() + ", Name: " + cn.get_name() + ", Duration: " + cn.get_duration() + ", Distance: " + cn.get_distance() + ", Level: " + cn.get_level();
            // Writing Cardio sessions to log
            Log.d(TAG, log);
        }

    }

    // generates the user interface for the cardio Activity
    private void handleUI(){

        TextView t1 = (TextView)findViewById(R.id.workout1Text);

    }



    public void onClickcardioBackButton(View view){
        v.vibrate(10);
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cardio, menu);
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

    public void cardioInputHandler(final Cardio c){

        // set cardio session date and time
        c.set_date(currentDate());
        c.set_time(currentTime());


        EditText timeField = (EditText) findViewById(R.id.CardioDurationEditText);

        // make sure that there is a field in duration if the user does not enter one
        if (timeField.getText().equals("")){
            c.set_duration("Not Entered");
        }
        else{
            c.set_duration(timeField.getText().toString());
        }



        // Spinner for workout 1
        Spinner cType_spinner = (Spinner) findViewById(R.id.cardioTypeSpinner);
        Spinner distance_Spinner = (Spinner) findViewById(R.id.CardioDistanceSpinner);
        Spinner level_Spinner = (Spinner) findViewById(R.id.cardioLevelSpinner);

        String[] names = new String[] { "Treadmill", "Elliptical ", "Stair-master", "Tabata Rows" , "Bicycle", "Track" };
        String[] distance = new String[] { "0.5", "1.0", "1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.9", "2.0", "2.1", "2.2", "2.3", "2.4", "2.5", "2.6", "2.7", "2.8", "2.9", "3.0", "3.1", "3.2", "3.3", "3.4", "3.5", "3.6", "3.7", "3.8", "3.9", "4.0"};
        String[] level = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"};


        ArrayAdapter<String> cardioNameAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, names);
        ArrayAdapter<String> distanceAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, distance);
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, level);

        // handles the cardio type input
        cType_spinner.setAdapter(cardioNameAdapter);
        cType_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c.set_name((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        // handles the distance input
        distance_Spinner.setAdapter(distanceAdapter);
        distance_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c.set_distance((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        // handles the level input
        level_Spinner.setAdapter(levelAdapter);
        level_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c.set_level((String) parent.getItemAtPosition(position));
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

    // retrieves the current date dynamically
    public String currentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat( "h:mm a");
        String currentTime = sdf.format(new Date());
        return currentTime;
    }


}