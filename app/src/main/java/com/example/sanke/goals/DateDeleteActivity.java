package com.example.sanke.goals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class DateDeleteActivity extends Activity {

    private static final String TAG = "sanke.goals";
    Vibrator vib;
    List<Workout> workouts;
    List<Cardio> cardio;
    String humanDate;
    String selDate;
    DatePicker datePickerDelete;
    TextView textViewUserDate;
    TextView textViewData;
    ScrollView dataContainer;

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create the date picker
        datePickerDelete = new DatePicker(this);

        Typeface elite = Typeface.createFromAsset(getAssets(), "SpecialElite.ttf");
        Typeface bear = Typeface.createFromAsset(getAssets(), "BEARPAW_.ttf");

        // hide the whole calendar view (works in api 11 or greater)
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= 11) {
            datePickerDelete.setCalendarViewShown(false);
        }

        // create the TextView
        textViewUserDate = new TextView(this);
        textViewUserDate.setGravity(Gravity.CENTER);
        textViewUserDate.setTextColor(Color.BLACK);
        textViewUserDate.setTextSize(20);

        // create the TextView
        textViewData = new TextView(this);
        textViewData.setGravity(Gravity.CENTER);
        textViewData.setTextColor(Color.BLACK);
        textViewData.setTextSize(17);

        dataContainer = new ScrollView(this);
        dataContainer.addView(textViewData);

        textViewUserDate.setTypeface(elite);
        textViewData.setTypeface(bear);

        textViewUserDate.setPadding(0, 30, 0, 0);
        textViewData.setPadding(0,20,0,0);

        // initialize the date to current date
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String dateStr = sdfDateTime.format(new Date(System.currentTimeMillis()));

        String[] dateSplit = dateStr.split("-");
        int currentYear = Integer.parseInt(dateSplit[0]);
        int currentMonth = Integer.parseInt(dateSplit[1]);
        int currentDay = Integer.parseInt(dateSplit[2]);

        //Log.d(TAG, "Seleted date: " + dateSplit[0] + dateSplit[1] + dateSplit[2]);

        selDate = dateSplit[0] + dateSplit[1] + dateSplit[2];
        workouts = db.getWorkoutsUsingDate(selDate);
        cardio = db.getCardioUsingDate(selDate);

        // to show date and day of week in the TextView
        setHumanReadableData(currentYear, currentMonth, currentDay, workouts, cardio);

        // initialize date picker listener
        // currentMonth - 1, because on the picker, 0 is January
        datePickerDelete.init(currentYear, currentMonth-1, currentDay, birthdayListener);


        // add to the container
        LinearLayout linearLayoutCalTvContainer = new LinearLayout(this);

        if(currentapiVersion < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            linearLayoutCalTvContainer.setBackgroundDrawable( getResources().getDrawable(R.mipmap.backgound15) );
        } else {
            //linearLayoutCalTvContainer.setBackgroundColor(0xFF3B3B3B);
            linearLayoutCalTvContainer.setBackground(getResources().getDrawable(R.mipmap.backgound15));
        }

        linearLayoutCalTvContainer.setOrientation(LinearLayout.VERTICAL);
        linearLayoutCalTvContainer.addView(datePickerDelete);
        linearLayoutCalTvContainer.addView(textViewUserDate);
        linearLayoutCalTvContainer.addView(dataContainer);

        // set the views for the activity
        setContentView(linearLayoutCalTvContainer);

    }

    // the date picker listener
    OnDateChangedListener birthdayListener = new OnDateChangedListener() {



        @Override
        public void onDateChanged(DatePicker birthDayDatePicker,
                                  int newYear, int newMonth, int newDay) {

            try{

                // currentMonth + 1, to retrieve proper month
                String year = Integer.toString(newYear);
                String month = Integer.toString(newMonth+1);
                String date = Integer.toString(newDay);


                switch (month) {
                    case "1":  month = "01";
                        break;
                    case "2":  month = "02";
                        break;
                    case "3":  month = "03";
                        break;
                    case "4":  month = "04";
                        break;
                    case "5":  month = "05";
                        break;
                    case "6":  month = "06";
                        break;
                    case "7":  month = "07";
                        break;
                    case "8":  month = "08";
                        break;
                    case "9":  month = "09";
                        break;
                    case "10":  month = "10";
                        break;
                    case "11":  month = "11";
                        break;
                    case "12":  month = "12";
                        break;
                    default: month = "Invalid month!";
                        break;
                }

                switch (date) {
                    case "1":  date = "01";
                        break;
                    case "2":  date = "02";
                        break;
                    case "3":  date = "03";
                        break;
                    case "4":  date = "04";
                        break;
                    case "5":  date = "05";
                        break;
                    case "6":  date = "06";
                        break;
                    case "7":  date = "07";
                        break;
                    case "8":  date = "08";
                        break;
                    case "9":  date = "09";
                        break;
                    default: date = date;
                        break;
                }

                //Log.d(TAG, "Selected date: " + year + month + date);
                selDate = year + month + date;

                workouts = db.getWorkoutsUsingDate(selDate);
                cardio = db.getCardioUsingDate(selDate);

                setHumanReadableData(newYear, newMonth + 1, newDay, workouts, cardio);



            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    // show the user a better date format
    public String setHumanReadableData(int newYear, int newMonth, int newDay, List<Workout> w, List<Cardio> c){
        try {
            String output = "";


            String clickedDate = newYear + "-" + newMonth + "-" + newDay;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(clickedDate);

            SimpleDateFormat sdfDateTime = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
            final String dateStr = sdfDateTime.format(d);

            humanDate = dateStr;

            for (Workout workout : w) {
                output += "Body part: " + workout.getBodypart() + ", workout is " + workout.getName();
                output += "\n";
            }

            for (Cardio cardio : c) {
                output += "Cardio Type: " + cardio.get_name() + ", at " + cardio.get_time();
                output += "\n";
            }

            textViewUserDate.setText(dateStr);
            textViewData.setText(output);

            textViewData.setOnLongClickListener(
                    new TextView.OnLongClickListener() {
                        public boolean onLongClick(View v) {

                            vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                            vib.vibrate(500);

                            // ask for validation on delete and delete if needed
                            confirmDelete(humanDate);
                            return true;
                        }
                    }
            );


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return humanDate;
    }

    public void confirmDelete(String hDate){

        new AlertDialog.Builder(this)
                .setTitle("Delete Session")
                .setMessage("Are you sure you want delete session on " + hDate)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        // delete the workout sessions on selected date
                        db.deleteWorkoutDate(selDate);

                        // delete the cardio sessions on selected date
                        db.deleteCardioDate(selDate);

                        Context context = getApplicationContext();
                        CharSequence text = "All sessions on " + selDate + " deleted!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        /*
                        Log.d(TAG, "Reading all contacts..");
                        List<Workout> workouts = db.getAllWorkouts();

                        for (Workout cn : workouts) {
                            String log = "Id: " + cn.getID() + " ,Date: " + cn.getDate() + " ,Body-part: " + cn.getBodypart() + " ,Name: " + cn.getName() + " ,Sets: " + cn.getSets() + " ,Reps: " + cn.getReps() + " ,Weight: " + cn.getWeight();
                            // Writing Workouts to log
                            Log.d(TAG, log);
                        }
                        */
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

}