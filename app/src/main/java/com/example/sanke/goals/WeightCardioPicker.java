package com.example.sanke.goals;

import android.content.Intent;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WeightCardioPicker extends AppCompatActivity {

    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_cardio_picker);


        v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        // Define the typefaces
        Typeface sport = Typeface.createFromAsset(getAssets(), "Sportrop.otf");


        Button cardioButton = (Button) findViewById(R.id.cardioButton);
        Button weightsButton = (Button) findViewById(R.id.weightsButton);

        cardioButton.setTypeface(sport);
        weightsButton.setTypeface(sport);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weight_cardio_picker, menu);
        return true;
    }

    // when weights button is clicked
    public void onClickWeights(View view){

        v.vibrate(30);


        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }

        Intent intent = new Intent(getApplicationContext(),MonthlyViewWeightsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    // when cardio button is clicked
    public void onClickCardio(View view){

        v.vibrate(30);

        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }

        Intent intent = new Intent(getApplicationContext(), MonthlyViewCardioActivity.class);
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
