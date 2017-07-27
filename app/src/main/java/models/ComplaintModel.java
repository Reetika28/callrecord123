package models;

/**
 * Created by ahextech on 25/7/17.
 */

public class ComplaintModel {

    public String name, loadged, by, description,date;
    public String reviews,subject,time, number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ComplaintModel(String name, String description, String date, String reviews, String subject, String time, String number, String key) {
        this.name = name;
        this.loadged = loadged;
        this.by = by;
        this.description = description;
        this.date = date;
        this.reviews = reviews;
        this.subject = subject;
        this.time = time;
        this.number = number;
        this.key = key;
    }

    public ComplaintModel(String name, String description, String date, String reviews, String subject, String time, String number) {
        this.name = name;
        this.time = time;
        this.description = description;
        this.date = date;
        this.reviews = reviews;
        this.subject = subject;
        this.time = time;
        this.number = number;

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ComplaintModel(String name, String loadged, String by, String description, String date, String reviews, String subject, String time, String key) {
        this.name = name;
        this.loadged = loadged;
        this.by = by;
        this.description = description;
        this.date = date;
        this.reviews = reviews;
        this.subject = subject;
        this.time = time;
        this.key = key;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String key;


    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public ComplaintModel(String name, String subject, String time, String description, String date, String reviews) {
        this.name = name;
        this.subject = subject;
        this.time = time;
        this.description = description;
        this.date = date;

        this.reviews = reviews;
        this.key = key;
    }

    public ComplaintModel() {
    }

    public ComplaintModel(String name, String subject, String time, String description, String date) {
        this.name = name;
        this.subject = subject;
        this.time = time;
        this.description = description;
        this.date = date;
    }

    public ComplaintModel(String subject, String time, String description) {
        this.subject = subject;
        this.time = time;
        this.description = description;
    }

    public ComplaintModel(String subject, String time, String description,String reviews) {
        this.subject = subject;
        this.time = time;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   /* public ComplaintModel(String loadged, String by,String key) {
        this.loadged = loadged;
        this.by = by;
        this.key = key;

    }
*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLoadged() {
        return loadged;
    }

    public void setLoadged(String loadged) {
        this.loadged = loadged;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
