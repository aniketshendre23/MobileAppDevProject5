package edu.uncc.assignment05;

public class Mood {
    int photo;
    String titleOfMood;

    public Mood(int photo, String titleOfMood) {
        this.photo = photo;
        this.titleOfMood = titleOfMood;
    }

    public int getPhoto() {
        return photo;
    }

    public String getTitleOfMood() {
        return titleOfMood;
    }

    @Override
    public String toString() {
        return "MoodData{" +
                "photo=" + photo +
                ", titleOfMood='" + titleOfMood + '\'' +
                '}';
    }
}
