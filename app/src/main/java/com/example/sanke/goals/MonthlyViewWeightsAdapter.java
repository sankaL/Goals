package com.example.sanke.goals;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by sanke on 03/09/2015.
 */
public class MonthlyViewWeightsAdapter extends ArrayAdapter<String> {

    private static final String TAG = "sanke.goals";

    public MonthlyViewWeightsAdapter(Context context, String[] wEntries) {
        super(context, R.layout.monthlylog_custom_row, wEntries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater sankesInflator = LayoutInflater.from(getContext());
        View customView = sankesInflator.inflate(R.layout.monthlylog_custom_row, parent, false);

        String singleWorkoutItem = getItem(position);
        ArrayList<String> pData = parseData(singleWorkoutItem);


        Button headingButton = (Button) customView.findViewById(R.id.headingButton);
        TextView subheadingText = (TextView) customView.findViewById(R.id.subheadingText);
        ImageView bpartImage = (ImageView) customView.findViewById(R.id.bodyPartImage);

        TextView infoText1 = (TextView) customView.findViewById(R.id.wo_Text1);
        TextView infoText2 = (TextView) customView.findViewById(R.id.wo_Text2);
        TextView infoText3 = (TextView) customView.findViewById(R.id.wo_Text3);
        TextView infoText4 = (TextView) customView.findViewById(R.id.wo_Text4);
        TextView infoText5 = (TextView) customView.findViewById(R.id.wo_Text5);
        TextView infoText6 = (TextView) customView.findViewById(R.id.wo_Text6);
        TextView infoText7 = (TextView) customView.findViewById(R.id.wo_Text7);

        TextView dataText1 = (TextView) customView.findViewById(R.id.dataText1);
        TextView dataText2 = (TextView) customView.findViewById(R.id.dataText2);
        TextView dataText3 = (TextView) customView.findViewById(R.id.dataText3);
        TextView dataText4 = (TextView) customView.findViewById(R.id.dataText4);
        TextView dataText5 = (TextView) customView.findViewById(R.id.dataText5);
        TextView dataText6 = (TextView) customView.findViewById(R.id.dataText6);
        TextView dataText7 = (TextView) customView.findViewById(R.id.dataText7);



        headingButton.setText(pData.get(0));
        subheadingText.setText(pData.get(1));

        infoText1.setText(pData.get(2));
        dataText1.setText(pData.get(3));

        infoText2.setText(pData.get(4));
        dataText2.setText(pData.get(5));

        infoText3.setText(pData.get(6));
        dataText3.setText(pData.get(7));

        infoText4.setText(pData.get(8));
        dataText4.setText(pData.get(9));

        infoText5.setText(pData.get(10));
        dataText5.setText(pData.get(11));

        infoText6.setText(pData.get(12));
        dataText6.setText(pData.get(13));

        infoText7.setText(pData.get(14));
        dataText7.setText(pData.get(15));


        Typeface capture_it = Typeface.createFromAsset(getContext().getAssets(), "Capture_it.ttf");
        Typeface elite = Typeface.createFromAsset(getContext().getAssets(), "SpecialElite.ttf");
        Typeface fff = Typeface.createFromAsset(getContext().getAssets(), "spincycle_ot.otf");
        Typeface typefaceData = Typeface.createFromAsset(getContext().getAssets(), "BEARPAW_.ttf");

        dataText1.setTypeface(typefaceData);
        dataText2.setTypeface(typefaceData);
        dataText3.setTypeface(typefaceData);
        dataText4.setTypeface(typefaceData);
        dataText5.setTypeface(typefaceData);
        dataText6.setTypeface(typefaceData);
        dataText7.setTypeface(typefaceData);

        infoText1.setTypeface(elite);
        infoText2.setTypeface(elite);
        infoText3.setTypeface(elite);
        infoText4.setTypeface(elite);
        infoText5.setTypeface(elite);
        infoText6.setTypeface(elite);
        infoText7.setTypeface(elite);
        headingButton.setTypeface(capture_it);
        subheadingText.setTypeface(fff);



        if (pData.get(0).equals("arms ")){
            bpartImage.setImageResource(R.mipmap.arms_caticon);
        }
        else if (pData.get(0).equals("chest ")){
            bpartImage.setImageResource(R.mipmap.chest_caticon);
        }
        else if (pData.get(0).equals("legs ")){
            bpartImage.setImageResource(R.mipmap.legs_caticon);
        }
        else if (pData.get(0).equals("shoulder-abs ")){
            bpartImage.setImageResource(R.mipmap.abs_caticon);
        }
        else if (pData.get(0).equals("back ")){
            bpartImage.setImageResource(R.mipmap.back_caticon);
        }
        else{
            Log.d(TAG, "ERROR: listView body part picture not defined");
        }




        return customView;
    }



    public static ArrayList<String> parseData(String data){

        ArrayList<String> parsedData = new ArrayList<String>();
        String formatedDate;

        String[] split1 = data.split("SPLIT");

        //System.out.println(split1[0]);

        String[] split2 = split1[0].split(",");

        //System.out.println(split2[1]);



        String bodyPart = split2[2].substring(11);
        String numDate = split2[1].substring(6, 14);


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

        parsedData.add(bodyPart);
        parsedData.add(formatedDate);

        for (String s: split1) {
            String[] split3 = s.split("Name: ");
            String[] split4 = split3[1].split("tagSanke");
            parsedData.add(split4[0]);
            parsedData.add(split4[1]);
        }

        return parsedData;

    }


}
