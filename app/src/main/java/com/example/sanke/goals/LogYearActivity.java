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
import android.widget.TextView;

public class LogYearActivity extends AppCompatActivity {

    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_year);
        v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        Typeface captureit = Typeface.createFromAsset(getAssets(), "Capture_it.ttf");

        Button b1 = (Button)findViewById(R.id.log2015Button);
        Button b2 = (Button)findViewById(R.id.log2016Button);
        Button b3 = (Button)findViewById(R.id.log2017Button);

        b1.setTypeface(captureit);
        b2.setTypeface(captureit);
        b3.setTypeface(captureit);

        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "Sportrop.otf");
        TextView heading = (TextView)findViewById(R.id.headingYearsText);
        heading.setTypeface(typeface2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_year, menu);
        return true;
    }

    public void onClickYear2015(View view){
        v.vibrate(10);
        Intent i = new Intent(this, LogMonthActivity.class);

        i.putExtra("yearMessage","2015");

        startActivity(i);
    }

    public void onClickYear2016(View view){
        v.vibrate(10);
        Intent i = new Intent(this, LogMonthActivity.class);

        i.putExtra("yearMessage","2016");

        startActivity(i);
    }

    public void onClickYear2017(View view){
        v.vibrate(10);
        Intent i = new Intent(this, LogMonthActivity.class);

        i.putExtra("yearMessage","2017");

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
