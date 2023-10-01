package edu.uncc.assignment05;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String age_group;
    Mood mood;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age_group='" + age_group + '\'' +
                ", mood=" + mood +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge_group() {
        return age_group;
    }

    public void setAge_group(String age_group) {
        this.age_group = age_group;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public User(String name, String age_group, Mood mood) {
        this.name = name;
        this.age_group = age_group;
        this.mood = mood;
    }
}
