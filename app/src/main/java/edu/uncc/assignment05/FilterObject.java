package edu.uncc.assignment05;

public class FilterObject {
    String selectedFilter;

    boolean set;

    public FilterObject(String selectedFilter, boolean set) {
        this.selectedFilter = selectedFilter;
        this.set = set;
    }

    public String getSelectedFilter() {
        return selectedFilter;
    }

    public boolean isSet() {
        return set;
    }

    public void setSelectedFilter(String selectedFilter) {
        this.selectedFilter = selectedFilter;
    }

    public void setSet(boolean set) {
        this.set = set;
    }
}
