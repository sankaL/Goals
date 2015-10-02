package com.example.sanke.goals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MonthlyViewWeightsActivity extends AppCompatActivity {

    private static final String TAG = "sanke.goals";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_view);

        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
        ArrayList<Workout> workList = bundle.getParcelableArrayList("MonthDataWeights");

        List<String> wData = extractWorkoutData(workList);
        String[] workoutEntries = wData.toArray(new String[wData.size()]);


        ListAdapter sankesAdapter = new MonthlyViewWeightsAdapter(this,workoutEntries);
        ListView sankesList = (ListView) findViewById(R.id.monthlyListView);
        sankesList.setAdapter(sankesAdapter);
    }



    public List<String> extractWorkoutData(ArrayList<Workout> workouts){

        // Initial value
        String bPart = "";
        String date = "";
        String entry = "";
        int entryLength = 1;
        List<String> entries = new ArrayList<String>();

        for (Workout cn : workouts) {

            // when the conditions are first met
            if (bPart.equals("") && date.equals(""))
            {
                //Log.v(TAG, "when the conditions are first met");
                date = cn.getDate();
                bPart = cn.getBodypart();
                entry = "Id: " + cn.getID() + " ,Date: " + cn.getDate() + " ,Body-part: " + cn.getBodypart() + " ,Name: " + cn.getName() + "tagSanke Sets: " + cn.getSets() + ", Reps: " + cn.getReps() + ", Weight: " + cn.getWeight();
                //Log.v(TAG, entry);
            }

            // when the body part and date equal the previous entry, which means its from the same session
            else if (date.equals(cn.getDate()) && bPart.equals(cn.getBodypart()))
            {
                //Log.v(TAG, "when the body part and date equal the previous entry, which means its from the same session");
                entry = entry + " SPLIT Id: " + cn.getID() + " ,Date: " + cn.getDate() + " ,Body-part: " + cn.getBodypart() + " ,Name: " + cn.getName() + "tagSanke Sets: " + cn.getSets() + ", Reps: " + cn.getReps() + ", Weight: " + cn.getWeight();

                // done so the workouts of same body part can be seperated to different entries by adding a workout limit of 7 per entry
                if (entryLength != 6){
                    entryLength ++;
                }
                else{
                    entries.add(entry);
                    entryLength = 1;
                    bPart = "";
                    date = "";
                }
            }
/*

            else if (bPart.equals(cn.getBodypart()))
            {
                // when the bodypart is the same but different date
                if (!date.equals(cn.getDate()))
                {
                    Log.v(TAG, "when the workout date is the same but different body part");
                    entries.add(entry);
                    date = cn.getDate();
                    bPart = cn.getBodypart();
                    entry = "Id: " + cn.getID() + " ,Date: " + cn.getDate() + " ,Body-part: " + cn.getBodypart() + " ,Name: " + cn.getName() + "tagSanke Sets: " + cn.getSets() + ", Reps: " + cn.getReps() + ", Weight: " + cn.getWeight();
                }
            }

            else if (date.equals(cn.getDate()))
            {
                // when the workout date is the same but different body part
                if (!bPart.equals(cn.getBodypart()))
                {
                    Log.v(TAG, "when the workout date is the same but different body part");
                    entries.add(entry);
                    date = cn.getDate();
                    bPart = cn.getBodypart();
                    entry = "Id: " + cn.getID() + " ,Date: " + cn.getDate() + " ,Body-part: " + cn.getBodypart() + " ,Name: " + cn.getName() + "tagSanke Sets: " + cn.getSets() + ", Reps: " + cn.getReps() + ", Weight: " + cn.getWeight();
                }
            }


            // when the body part is different on a different date
            else if (!date.equals(cn.getDate()) && !bPart.equals(cn.getBodypart()))
            {
                    Log.v(TAG, "when the body part is different on a different date");
                    entries.add(entry);
                    date = cn.getDate();
                    bPart = cn.getBodypart();
                    entry = "Id: " + cn.getID() + " ,Date: " + cn.getDate() + " ,Body-part: " + cn.getBodypart() + " ,Name: " + cn.getName() + "tagSanke Sets: " + cn.getSets() + ", Reps: " + cn.getReps() + ", Weight: " + cn.getWeight();
            }


            // unidentified situation
            else
            {
                Log.v(TAG, " unidentified situation");
                Log.d(TAG, "ERROR: extractWorkoutData() --> unidentified situation");

            }
*/
        }

        return entries;

    }


    public void onClickMoreButton(View view){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_monthly_view, menu);
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
