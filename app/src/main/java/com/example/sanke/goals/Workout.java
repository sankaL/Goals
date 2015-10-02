package com.example.sanke.goals;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sanke on 01/09/2015.
 */
public class Workout implements Parcelable {

    //private variables
    int _id;
    String _date;
    String _bodypart;
    String _name;
    String _sets;
    String _reps;
    String _weight;

    // Empty constructor
    public Workout(){

    }



    // constructor
    public Workout(int id, String _date, String _bodypart, String name,  String _sets, String _reps, String _weight){
        this._id = id;
        this._date = _date;
        this._name = name;
        this._bodypart = _bodypart;
        this._sets = _sets;
        this._reps = _reps;
        this._weight = _weight;
    }

    // constructor
    public Workout(String _date, String _bodypart, String name,  String _sets, String _reps, String _weight){
        this._date = _date;
        this._bodypart = _bodypart;
        this._name = name;
        this._sets = _sets;
        this._reps = _reps;
        this._weight = _weight;
    }

    public Workout(Parcel in){
        _id = in.readInt();
        _date = in.readString();
        _bodypart = in.readString();
        _name = in.readString();
        _sets = in.readString();
        _reps = in.readString();
        _weight = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(_date);
        dest.writeString(_bodypart);
        dest.writeString(_name);
        dest.writeString(_sets);
        dest.writeString(_reps);
        dest.writeString(_weight);
    }

    public static final Parcelable.Creator<Workout> CREATOR = new Parcelable.Creator<Workout>()
    {
        public Workout createFromParcel(Parcel in)
        {
            return new Workout(in);
        }
        public Workout[] newArray(int size)
        {
            return new Workout[size];
        }
    };

    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting date
    public String getDate(){
        return this._date;
    }

    // setting date
    public void setBodypart(String bodypart){
        this._bodypart = bodypart;
    }

    // getting body part
    public String getBodypart(){
        return this._bodypart;
    }

    // setting body part
    public void setDate(String phone_number){
        this._date = phone_number;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }


    // getting sets
    public String getSets(){
        return this._sets;
    }

    // setting sets
    public void setSets(String sets){
        this._sets = sets;
    }

    // getting reps
    public String getReps(){
        return this._reps;
    }

    // setting reps
    public void setReps(String reps){
        this._reps = reps;
    }

    // getting weights
    public String getWeight(){
        return this._weight;
    }

    // setting weights
    public void setWeight(String weight){
        this._weight = weight;
    }


}

