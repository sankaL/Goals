package com.example.sanke.goals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MonthlyViewCardioActivity extends AppCompatActivity {

    private static final String TAG = "sanke.goals";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_view_cardio);

        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
        ArrayList<Cardio> cardioList = bundle.getParcelableArrayList("MonthDataCardio");

        List<String> cData = extractCardioData(cardioList);
        String[] cardioEntries = cData.toArray(new String[cData.size()]);

        Log.d(TAG, "Entries...");

        ListAdapter cardioAdapter = new MonthlyViewCardioAdapter(this,cardioEntries);
        ListView cardioListView = (ListView) findViewById(R.id.monthlyCardioListView);
        cardioListView.setAdapter(cardioAdapter);
    }

    public List<String> extractCardioData(ArrayList<Cardio> cardio) {

        // Initial value
        String entry = "";
        List<String> entries = new ArrayList<String>();

        for (Cardio cn : cardio) {
            entry = "Id: " + cn.get_id() + ", Date: " + cn.get_date() + ", Time: " + cn.get_time() + ", Name: " + cn.get_name() + ", Duration: " + cn.get_duration() + ", Distance: " + cn.get_distance() + ", Level: " + cn.get_level();
            entries.add(entry);

        }

        return entries;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_monthly_view_cardio, menu);
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
