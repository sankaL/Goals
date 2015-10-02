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
import android.widget.EditText;

import java.util.List;

public class SessionActivity extends AppCompatActivity {

    private static final String TAG = "sanke.goals";
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        Typeface captureit = Typeface.createFromAsset(getAssets(), "Capture_it.ttf");

        Button b1 = (Button)findViewById(R.id.armButton);
        Button b2 = (Button)findViewById(R.id.legsButton);
        Button b3 = (Button)findViewById(R.id.chestButton);
        Button b4 = (Button)findViewById(R.id.backButton);
        Button b5 = (Button)findViewById(R.id.shoulderButton);
        Button b6 = (Button)findViewById(R.id.cardioButton);

        b1.setTypeface(captureit);
        b2.setTypeface(captureit);
        b3.setTypeface(captureit);
        b4.setTypeface(captureit);
        b5.setTypeface(captureit);
        b6.setTypeface(captureit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_session, menu);
        return true;
    }

    public void onClickArms(View view){
        v.vibrate(10);
        Intent i = new Intent(this, ArmsActivity.class);

        // send to workout activity that its a arms workout
        String bodyPart = "arms";
        i.putExtra("workoutMessage",bodyPart);

        startActivity(i);
    }

    public void onClickLegs(View view){
        v.vibrate(10);
        Intent i = new Intent(this, ArmsActivity.class);

        // send to workout activity that its a arms workout
        String bodyPart = "legs";
        i.putExtra("workoutMessage",bodyPart);

        startActivity(i);
    }

    public void onClickChest(View view){
        v.vibrate(10);
        Intent i = new Intent(this, ArmsActivity.class);

        // send to workout activity that its a arms workout
        String bodyPart = "chest";
        i.putExtra("workoutMessage",bodyPart);

        startActivity(i);
    }

    public void onClickBack(View view){
        v.vibrate(10);
        Intent i = new Intent(this, ArmsActivity.class);

        // send to workout activity that its a arms workout
        String bodyPart = "back";
        i.putExtra("workoutMessage",bodyPart);

        startActivity(i);
    }

    public void onClickShoulders(View view){
        v.vibrate(10);
        Intent i = new Intent(this, ArmsActivity.class);

        // send to workout activity that its a arms workout
        String bodyPart = "shoulder-abs";
        i.putExtra("workoutMessage",bodyPart);

        startActivity(i);
    }

    public void onClickCardio(View view){
        v.vibrate(10);
        Intent i = new Intent(this, CardioActivity.class);
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
