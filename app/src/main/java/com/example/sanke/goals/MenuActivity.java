package com.example.sanke.goals;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        setContentView(R.layout.activity_menu);

        Typeface captureit = Typeface.createFromAsset(getAssets(), "Capture_it.ttf");

        Button b1 = (Button)findViewById(R.id.startSessionButton);
        Button b2 = (Button)findViewById(R.id.workoutLogButton);
        Button b3 = (Button)findViewById(R.id.deleteSessionButton);
        Button b4 = (Button)findViewById(R.id.backupButton);

        b1.setTypeface(captureit);
        b2.setTypeface(captureit);
        b3.setTypeface(captureit);
        b4.setTypeface(captureit);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }


    public void onClickStartSession(View view){
        v.vibrate(10);
        Intent i = new Intent(this, ProgramsActivity.class);
        startActivity(i);
    }

    public void onClickDeleteSession(View view){
        v.vibrate(10);
        Intent i = new Intent(this, DateDeleteActivity.class);
        startActivity(i);
    }

    public void onClickWorkoutLog(View view){
        v.vibrate(10);
        Intent i = new Intent(this, LogYearActivity.class);
        startActivity(i);
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
