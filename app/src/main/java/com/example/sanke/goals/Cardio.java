package com.example.sanke.goals;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sanke on 01/09/2015.
 */
public class Cardio implements Parcelable {

    //private variables
    int _id;
    String _date;
    String _time;
    String _name;
    String _duration;
    String _distance;
    String _level;

    // Empty constructor
    public Cardio(){

    }

    // constructor
    public Cardio(int id, String _date, String _time, String _name,    String _duration, String _distance, String _level){
        this._id = id;
        this._date = _date;
        this._name = _name;
        this._time = _time;
        this._duration = _duration;
        this._distance = _distance;
        this._level = _level;
    }

    // constructor
    public Cardio(String _date, String _time, String _name,    String _duration, String _distance, String _level){
        this._date = _date;
        this._name = _name;
        this._time = _time;
        this._duration = _duration;
        this._distance = _distance;
        this._level = _level;
    }

    public Cardio(Parcel in){
        _id = in.readInt();
        _date = in.readString();
        _time = in.readString();
        _name = in.readString();
        _duration = in.readString();
        _distance = in.readString();
        _level = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(_date);
        dest.writeString(_time);
        dest.writeString(_name);
        dest.writeString(_duration);
        dest.writeString(_distance);
        dest.writeString(_level);
    }

    public static final Parcelable.Creator<Cardio> CREATOR = new Parcelable.Creator<Cardio>()
    {
        public Cardio createFromParcel(Parcel in)
        {
            return new Cardio(in);
        }
        public Cardio[] newArray(int size)
        {
            return new Cardio[size];
        }
    };

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public String get_duration() {
        return _duration;
    }

    public void set_duration(String _duration) {
        this._duration = _duration;
    }

    public String get_distance() {
        return _distance;
    }

    public void set_distance(String _distance) {
        this._distance = _distance;
    }

    public String get_level() {
        return _level;
    }

    public void set_level(String _level) {
        this._level = _level;
    }
}

