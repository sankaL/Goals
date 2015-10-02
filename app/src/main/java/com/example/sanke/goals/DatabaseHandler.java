package com.example.sanke.goals;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "workoutsManagerTEST";

	// Workouts table name
	private static final String TABLE_WORKOUTS = "workouts";

	// Cardio table name
	private static final String TABLE_CARDIO = "cardios";

	// Workouts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_DATE = "date";
	private static final String KEY_BODYPART = "bodypart";
	private static final String KEY_NAME = "name";
	private static final String KEY_SETS = "sets";
	private static final String KEY_REPS = "reps";
	private static final String KEY_WEIGHT = "weight";

	// Cardio Table Columns names
	private static final String KEY_ID_CAR = "id";
	private static final String KEY_DATE_CAR = "date";
	private static final String KEY_TIME_CAR = "time";
	private static final String KEY_NAME_CAR = "name";
	private static final String KEY_DURATION_CAR = "duration";
	private static final String KEY_DISTANCE_CAR = "distance";
	private static final String KEY_LEVEL_CAR = "level";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_WORKOUTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE + " TEXT,"
				+ KEY_BODYPART + " TEXT," + KEY_NAME + " TEXT," + KEY_SETS + " TEXT," + KEY_REPS + " TEXT," + KEY_WEIGHT + " TEXT" + ");";

		String CREATE_CARDIO_TABLE = "CREATE TABLE " + TABLE_CARDIO + "("
				+ KEY_ID_CAR + " INTEGER PRIMARY KEY," + KEY_DATE_CAR + " TEXT,"
				+ KEY_TIME_CAR + " TEXT," + KEY_NAME_CAR + " TEXT," + KEY_DURATION_CAR + " TEXT," + KEY_DISTANCE_CAR + " TEXT," + KEY_LEVEL_CAR + " TEXT" + ");";

		db.execSQL(CREATE_CONTACTS_TABLE);
		db.execSQL(CREATE_CARDIO_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDIO);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new workout
	void addWorkout(Workout workout) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_DATE, workout.getDate());
		values.put(KEY_BODYPART, workout.getBodypart());
		values.put(KEY_NAME, workout.getName());
		values.put(KEY_SETS, workout.getSets());
		values.put(KEY_REPS, workout.getReps());
		values.put(KEY_WEIGHT, workout.getWeight());

		// Inserting Row
		db.insert(TABLE_WORKOUTS, null, values);
		db.close(); // Closing database connection
	}

	// Adding new cardio session
	void addCardio(Cardio cardio) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_DATE_CAR, cardio.get_date());
		values.put(KEY_TIME_CAR, cardio.get_time());
		values.put(KEY_NAME_CAR, cardio.get_name());
		values.put(KEY_DURATION_CAR, cardio.get_duration());
		values.put(KEY_DISTANCE_CAR, cardio.get_distance());
		values.put(KEY_LEVEL_CAR, cardio.get_level());

		// Inserting Row
		db.insert(TABLE_CARDIO, null, values);
		db.close(); // Closing database connection
	}


	// Getting all workouts on the date
	public List<Workout> getWorkoutsUsingDate(String date) {
			List<Workout> workoutList = new ArrayList<Workout>();
			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_WORKOUTS + " WHERE " + KEY_DATE + "=" + date;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Workout workout = new Workout();
					workout.setID(Integer.parseInt(cursor.getString(0)));
					workout.setDate(cursor.getString(1));
					workout.setBodypart(cursor.getString(2));
					workout.setName(cursor.getString(3));
					workout .setSets(cursor.getString(4));
					workout.setReps(cursor.getString(5));
					workout.setWeight(cursor.getString(6));
					// Adding contact to list
					workoutList.add(workout);
				} while (cursor.moveToNext());
			}

			// return contact list
			return workoutList;
	}

	// Getting all cardio sessions on the date
	public List<Cardio> getCardioUsingDate(String date) {
		List<Cardio> cardioList = new ArrayList<Cardio>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CARDIO + " WHERE " + KEY_DATE_CAR + "=" + date;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Cardio cardio = new Cardio();
				cardio.set_id(Integer.parseInt(cursor.getString(0)));
				cardio.set_date(cursor.getString(1));
				cardio.set_time(cursor.getString(2));
				cardio.set_name(cursor.getString(3));
				cardio.set_duration(cursor.getString(4));
				cardio.set_distance(cursor.getString(5));
				cardio.set_level(cursor.getString(6));
				// Adding cardio sesh to list
				cardioList.add(cardio);
			} while (cursor.moveToNext());
		}

		// return contact list
		return cardioList;
	}

	// Getting all workouts on the month
	public ArrayList<Workout> getWorkoutsUsingMonth(String month) {
		ArrayList<Workout> workoutList = new ArrayList<Workout>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_WORKOUTS + " WHERE " + KEY_DATE + " LIKE " + "'" + month + "%'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Workout workout = new Workout();
				workout.setID(Integer.parseInt(cursor.getString(0)));
				workout.setDate(cursor.getString(1));
				workout.setBodypart(cursor.getString(2));
				workout.setName(cursor.getString(3));
				workout.setSets(cursor.getString(4));
				workout.setReps(cursor.getString(5));
				workout.setWeight(cursor.getString(6));
				// Adding contact to list
				workoutList.add(workout);
			} while (cursor.moveToNext());
		}

		// return contact list
		return workoutList;
	}

	// Getting all cardio sessions on the month
	public ArrayList<Cardio> getCardiosUsingMonth(String month) {

		ArrayList<Cardio> cardioList = new ArrayList<Cardio>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CARDIO + " WHERE " + KEY_DATE_CAR + " LIKE " + "'" + month + "%'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Cardio cardio = new Cardio();
				cardio.set_id(Integer.parseInt(cursor.getString(0)));
				cardio.set_date(cursor.getString(1));
				cardio.set_time(cursor.getString(2));
				cardio.set_name(cursor.getString(3));
				cardio.set_duration(cursor.getString(4));
				cardio.set_distance(cursor.getString(5));
				cardio.set_level(cursor.getString(6));
				// Adding cardio sesh to list
				cardioList.add(cardio);
			} while (cursor.moveToNext());
		}

		// return contact list
		return cardioList;
	}

	
	// Getting All Workouts
	public List<Workout> getAllWorkouts() {
		List<Workout> workoutList = new ArrayList<Workout>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_WORKOUTS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Workout workout = new Workout();
				workout.setID(Integer.parseInt(cursor.getString(0)));
				workout.setDate(cursor.getString(1));
				workout.setBodypart(cursor.getString(2));
				workout.setName(cursor.getString(3));
				workout.setSets(cursor.getString(4));
				workout.setReps(cursor.getString(5));
				workout.setWeight(cursor.getString(6));
				// Adding contact to list
				workoutList.add(workout);
			} while (cursor.moveToNext());
		}

		// return workout list
		return workoutList;
	}

	// Getting All Cardio sessions
	public List<Cardio> getAllCardio() {
		List<Cardio> cardioList = new ArrayList<Cardio>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CARDIO;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Cardio cardio = new Cardio();
				cardio.set_id(Integer.parseInt(cursor.getString(0)));
				cardio.set_date(cursor.getString(1));
				cardio.set_time(cursor.getString(2));
				cardio.set_name(cursor.getString(3));
				cardio.set_duration(cursor.getString(4));
				cardio.set_distance(cursor.getString(5));
				cardio.set_level(cursor.getString(6));
				// Adding cardio sesh to list
				cardioList.add(cardio);
			} while (cursor.moveToNext());
		}

		// return workout list
		return cardioList;
	}



	// Deleting single workout using date
	public void deleteWorkoutDate(String date) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_WORKOUTS, KEY_DATE + " = ?", new String[] { date });
		db.close();
	}


	// Deleting single cardio session using date
	public void deleteCardioDate(String date) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CARDIO, KEY_DATE_CAR + " = ?", new String[] { date });
		db.close();
	}



	// Getting single workout using ID
	Workout getWorkout(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_WORKOUTS, new String[]{KEY_ID, KEY_DATE, KEY_BODYPART, KEY_NAME, KEY_SETS, KEY_REPS, KEY_WEIGHT}, KEY_ID + "=?",
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Workout workout = new Workout(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
		// return contact
		return workout;
	}

	// Updating single contact
	public int updateWorkout(Workout workout) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_DATE, workout.getDate());
		values.put(KEY_BODYPART, workout.getDate());
		values.put(KEY_NAME, workout.getName());
		values.put(KEY_SETS, workout.getSets());
		values.put(KEY_REPS, workout.getReps());
		values.put(KEY_WEIGHT, workout.getWeight());

		// updating row
		return db.update(TABLE_WORKOUTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(workout.getID()) });
	}

	// Deleting single contact
	public void deleteWorkout(Workout workout) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_WORKOUTS, KEY_ID + " = ?",
				new String[]{String.valueOf(workout.getID())});
		db.close();
	}



	// Getting contacts Count
	public int getWorkoutsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_WORKOUTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}
