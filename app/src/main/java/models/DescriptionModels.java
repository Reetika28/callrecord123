package models;

import android.util.Log;

/**
 * Created by ahextech on 12/7/17.
 */

public class DescriptionModels  {

    public  String description, firnumber;
   public String complaintName, phoneNumber;
   public String date, time;
    public String reviews;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    String key;

    public DescriptionModels(){

    }

    public DescriptionModels(String description, String complaintName, String firnumber, String phoneNumber, String date, String time) {
        this.description = description;
        this.complaintName = complaintName;
        this.firnumber = firnumber;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.time = time;
    }

    public DescriptionModels(String firnumber, String description) {
        this.description = description;
        this.firnumber = firnumber;

    }

    public DescriptionModels(String description, String firnumber, String complaintName, String phoneNumber, String date, String time, String reviews) {
        this.description = description;
        this.firnumber = firnumber;
        this.complaintName = complaintName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.time = time;
        this.reviews = reviews;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getFirnumber() {
        return firnumber;
    }

    public void setFirnumber(String firnumber) {
        this.firnumber = firnumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComplaintName() {
        return complaintName;
    }

    public void setComplaintName(String complaintName) {
        this.complaintName = complaintName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
