package edu.uncc.assignment05;

public class SortObject {
    int direction;
    boolean set;

    public SortObject(int direction, boolean set) {
        this.direction = direction;
        this.set = set;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isSet() {
        return set;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setSet(boolean set) {
        this.set = set;
    }
}
