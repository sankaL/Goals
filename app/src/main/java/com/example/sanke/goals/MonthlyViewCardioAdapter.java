package com.example.sanke.goals;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MonthlyViewCardioAdapter  extends ArrayAdapter<String> {

    private static final String TAG = "sanke.goals";

    public MonthlyViewCardioAdapter(Context context, String[] cEntries) {
        super(context, R.layout.monthlylog_custom_row, cEntries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater sankesInflator = LayoutInflater.from(getContext());
        View customView = sankesInflator.inflate(R.layout.monthlylog_cardio_custom_row, parent, false);

        // Define the typefaces
        Typeface capture_it = Typeface.createFromAsset(getContext().getAssets(), "Capture_it.ttf");
        Typeface sport = Typeface.createFromAsset(getContext().getAssets(), "Sportrop.otf");
        Typeface fff = Typeface.createFromAsset(getContext().getAssets(), "spincycle_ot.otf");
        Typeface typefaceData = Typeface.createFromAsset(getContext().getAssets(), "BEARPAW_.ttf");

        String singleCardioItem = getItem(position);

        // Use helper function to parse the string and add each data field to a String arrayList
        ArrayList<String> parsedData = parseData(singleCardioItem);

        //TextView DateTextViewCAR1 = (TextView) customView.findViewById(R.id.DateTextViewCAR1);
        TextView DateTextViewCAR2 = (TextView) customView.findViewById(R.id.DateTextViewCAR2);
        DateTextViewCAR2.setTypeface(sport);
        //TextView TimeTextViewCAR1 = (TextView) customView.findViewById(R.id.TimeTextViewCAR1);
        TextView TimeTextViewCAR2 = (TextView) customView.findViewById(R.id.TimeTextViewCAR2);
        TimeTextViewCAR2.setTypeface(fff);
        TextView NameTextViewCAR1 = (TextView) customView.findViewById(R.id.NameTextViewCAR1);
        NameTextViewCAR1.setTypeface(capture_it);
        TextView NameTextViewCAR2 = (TextView) customView.findViewById(R.id.NameTextViewCAR2);
        NameTextViewCAR2.setTypeface(typefaceData);
        TextView DurationViewCAR1 = (TextView) customView.findViewById(R.id.DurationViewCAR1);
        DurationViewCAR1.setTypeface(capture_it);
        TextView DurationViewCAR2 = (TextView) customView.findViewById(R.id.DurationViewCAR2);
        DurationViewCAR2.setTypeface(typefaceData);
        TextView DistanceViewCAR1 = (TextView) customView.findViewById(R.id.DistanceViewCAR1);
        DistanceViewCAR1.setTypeface(capture_it);
        TextView DistanceViewCAR2 = (TextView) customView.findViewById(R.id.DistanceViewCAR2);
        DistanceViewCAR2.setTypeface(typefaceData);
        TextView LevelViewCAR1 = (TextView) customView.findViewById(R.id.LevelViewCAR1);
        LevelViewCAR1.setTypeface(capture_it);
        TextView LevelViewCAR2 = (TextView) customView.findViewById(R.id.LevelViewCAR2);
        LevelViewCAR2.setTypeface(typefaceData);


        RelativeLayout relativeLayoutCardio = (RelativeLayout) customView.findViewById(R.id.relativeLayoutCardio);

        // Display the right background according to cardio session
        if(parsedData.get(2).equals("Treadmill")){
            relativeLayoutCardio.setBackground(getContext().getDrawable(R.mipmap.treadmill_back));
        }
        else if (parsedData.get(2).equals("Elliptical ")){
            relativeLayoutCardio.setBackground(getContext().getDrawable(R.mipmap.elliptical_back));
        }
        else if (parsedData.get(2).equals("Stair-master")){
            relativeLayoutCardio.setBackground(getContext().getDrawable(R.mipmap.stairs_back));

        }
        else if (parsedData.get(2).equals("Tabata Rows")){
            relativeLayoutCardio.setBackground(getContext().getDrawable(R.mipmap.rowing_back));

        }
        else if (parsedData.get(2).equals("Bicycle")){
            relativeLayoutCardio.setBackground(getContext().getDrawable(R.mipmap.cycle_back));

        }
        else if (parsedData.get(2).equals("Track")){
            relativeLayoutCardio.setBackground(getContext().getDrawable(R.mipmap.track_back));

        }
        else{
            relativeLayoutCardio.setBackgroundColor(0xFF3B3B3B);
        }

            //"Treadmill", "Elliptical ", "Stair-master", "Tabata Rows" , "Bicycle", "Track" };
        // Display the data fields
        DateTextViewCAR2.setText(parsedData.get(0));
        TimeTextViewCAR2.setText(parsedData.get(1));
        NameTextViewCAR2.setText(parsedData.get(2));
        DurationViewCAR2.setText(parsedData.get(3));
        DistanceViewCAR2.setText(parsedData.get(4));
        LevelViewCAR2.setText(parsedData.get(5));



        return customView;
    }


    // Helper function to parse the data given to the adapter
    // Adds the useful data into a String arrayList and returns it
    public static ArrayList<String> parseData(String data){

        ArrayList<String> parsedData = new ArrayList<String>();
        String formatedDate;

        // split the string into seperate key-value pairs eg. Date: 20150918
        String[] fields = data.split(",");

        // extract the date
        String numDate = fields[1].substring(7, 15);


        if (numDate.substring(4,6).equals("01")) {
            formatedDate = "January " + numDate.substring(6,8) + 	", " + numDate.substring(0,4);
        }
        else if (numDate.substring(4,6).equals("02")) {
            formatedDate = "February " + numDate.substring(6,8) + 	", " + numDate.substring(0,4);
        }
        else if (numDate.substring(4,6).equals("03")) {
            formatedDate = "March " + numDate.substring(6,8) + 	", " + numDate.substring(0,4);
        }
        else if (numDate.substring(4,6).equals("04")) {
            formatedDate = "April " + numDate.substring(6,8) + 	", " + numDate.substring(0,4);
        }
        else if (numDate.substring(4,6).equals("05")) {
            formatedDate = "May " + numDate.substring(6,8) + 	", " + numDate.substring(0,4);
        }
        else if (numDate.substring(4,6).equals("06")) {
            formatedDate = "June " + numDate.substring(6,8) + 	", " + numDate.substring(0,4);
        }
        else if (numDate.substring(4,6).equals("07")) {
            formatedDate = "July " + numDate.substring(6,8) + 	", " + numDate.substring(0,4);
        }
        else if (numDate.substring(4,6).equals("08")) {
            formatedDate = "August " + numDate.substring(6,8) + 	", " + numDate.substring(0,4);
        }
        else if (numDate.substring(4,6).equals("09")) {
            formatedDate = "September " + numDate.substring(6,8) + 	", " + numDate.substring(0,4);
        }
        else if (numDate.substring(4,6).equals("10")) {
            formatedDate = "October " + numDate.substring(6,8) + 	", " + numDate.substring(0,4);
        }
        else if (numDate.substring(4,6).equals("11")) {
            formatedDate = "November " + numDate.substring(6,8) + 	", " + numDate.substring(0,4);
        }
        else if (numDate.substring(4,6).equals("12")) {
            formatedDate = "December " + numDate.substring(6,8) + 	", " + numDate.substring(0,4);
        }
        else
        {
            formatedDate = "ERROR: could not format date";
        }


        // add the formated date to the parsed data string arrayList
        parsedData.add(formatedDate);

        for (String s: fields){

            String[] keyValue = s.split(": ");

            if(!keyValue[0].substring(1).equals("d") && !keyValue[0].substring(1).equals("Date"))
            {
                // add the value from key value pair to the string arraylist
                parsedData.add(keyValue[1]);
            }
            else
            {
                // error handle if id field or the numeric date was picked up
            }

        }



        return parsedData;

    }

}
