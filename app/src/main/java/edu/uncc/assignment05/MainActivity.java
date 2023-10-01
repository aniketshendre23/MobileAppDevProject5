// Assignment 5
// MainActivity.java
// Mihir Phatak and Aniket Shendre - Group 3

package edu.uncc.assignment05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements UsersFragment.UsersFragmentListener, AddUserFragment.AddUserFragmentListener, SelectAgeGroupFragment.SelectAgeGroupInterface, SelectMoodFragment.SelectMoodFragmentListener, SortFragment.SortFragmentListener, FilterFragment.FilterFragmentListener, ProfileFragment.ProfileFragmentListener {

    ArrayList<User> mUsers = new ArrayList<>();
    Hashtable<String, SortObject> keySort = new Hashtable<>();
    Hashtable<String, FilterObject> keyFilter = new Hashtable<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keySort.put("name", new SortObject(1, false));
        keySort.put("age", new SortObject(1, false));
        keySort.put("mood", new SortObject(1, false));

        keyFilter.put("name", new FilterObject("", false));
        keyFilter.put("age", new FilterObject("", false));
        keyFilter.put("mood", new FilterObject("", false));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new UsersFragment(), "users-fragment")
                .commit();

    }

    @Override
    public void gotoAddUser() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new AddUserFragment(), "add-user-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void clear() {
        mUsers.clear();
        UsersFragment fragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if (fragment != null) {
            fragment.setUsersList(mUsers);
        }
    }

    @Override
    public void removeUser(User user) {
        mUsers.remove(user);
        ArrayList<User> list = filter();
        UsersFragment fragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if (fragment != null) {
            fragment.setUsersList(list);
        }
    }

    @Override
    public void gotoSort() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SortFragment(), "sort-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoFilter() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, FilterFragment.newInstance(mUsers), "filter-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoProfile(User user) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ProfileFragment.newInstance(user), "profile-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoSelectAgeGroup() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectAgeGroupFragment(), "select-age-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoSelectMood() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectMoodFragment(), "select-mood-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void submit(User user) {
        mUsers.add(user);
        ArrayList<User> list = filter();
        UsersFragment usersFragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if (usersFragment != null) {
            usersFragment.setUsersList(list);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void addAgeGroup(String age) {
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("add-user-fragment");
        if (fragment != null) {
            fragment.setAgeGroup(age);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelAgeGroupSelection() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void addMood(Mood mood) {
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("add-user-fragment");
        if (fragment != null) {
            fragment.setMood(mood);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelMoodSelection() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void setSortName(int direction, String name) {
        clearSort();
        keySort.put("name", new SortObject(direction, true));
        ArrayList<User> toSet = filter();

        UsersFragment usersFragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if (usersFragment != null) {
            usersFragment.setUsersList(toSet);
            usersFragment.setSortName(name);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void setSortAgeGroup(int direction, String name) {
        clearSort();
        keySort.put("age", new SortObject(direction, true));
        ArrayList<User> toSet = filter();
        UsersFragment usersFragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if (usersFragment != null) {
            usersFragment.setUsersList(toSet);
            usersFragment.setSortName(name);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void setSortFeeling(int direction, String name) {
        clearSort();
        keySort.put("mood", new SortObject(direction, true));
        ArrayList<User> toSet = filter();
        UsersFragment usersFragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if (usersFragment != null) {
            usersFragment.setUsersList(toSet);
            usersFragment.setSortName(name);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelSort() {
        clearSort();
        UsersFragment usersFragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if (usersFragment != null) {
            usersFragment.setUsersList(mUsers);
            usersFragment.setSortName("No Sort");
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void applyNameFilter(String start, String name) {
        clearAllFilter();
        keyFilter.put("name", new FilterObject(start, true));
        ArrayList<User> toSet = filter();
        UsersFragment usersFragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if (usersFragment != null) {
            usersFragment.setUsersList(toSet);
            usersFragment.setFilterName(name + " (" + start + ")");
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void applyAgeFilter(String age, String name) {
        clearAllFilter();
        keyFilter.put("age", new FilterObject(age, true));
        ArrayList<User> toSet = filter();
        UsersFragment usersFragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if (usersFragment != null) {
            usersFragment.setUsersList(toSet);
            usersFragment.setFilterName(name + " " + age);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void applyMoodFilter(int mood, String name) {
        clearAllFilter();
        keyFilter.put("mood", new FilterObject(Integer.toString(mood), true));
        ArrayList<User> toSet = filter();
        UsersFragment usersFragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if (usersFragment != null) {
            usersFragment.setUsersList(toSet);
            usersFragment.setFilterName(name);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void closeFilter() {
        clearAllFilter();
        UsersFragment usersFragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("users-fragment");
        if (usersFragment != null) {
            usersFragment.setUsersList(mUsers);
            usersFragment.setFilterName("NO FILTER");
        }
        getSupportFragmentManager().popBackStack();
    }

    public ArrayList<User> filter() {
        ArrayList<User> list = (ArrayList<User>) mUsers.clone();
        for (String key : keySort.keySet()) {
            if (keySort.get(key).isSet()) {
                if (key.equals("name")) {
                    sortByName(list, keySort.get(key).getDirection());
                } else if (key.equals("age")) {
                    sortByAge(list, keySort.get(key).getDirection());
                } else if (key.equals("mood")) {
                    sortByMood(list, keySort.get(key).getDirection());
                }
                break;
            }
        }
        for (String key : keyFilter.keySet()) {
            if (keyFilter.get(key).isSet()) {
                if (key.equals("name")) {
                    return filterName(list, keyFilter.get(key).getSelectedFilter());
                } else if (key.equals("age")) {
                    return filterByAge(list, keyFilter.get(key).getSelectedFilter());
                } else if (key.equals("mood")) {
                    return filterByMood(list, Integer.parseInt(keyFilter.get(key).getSelectedFilter()));
                }

                break;
            }
        }
        return list;
    }


    public void sortByName(ArrayList<User> userArrayList, int direction) {
        if (direction == 1) {
            Collections.sort(userArrayList, new Comparator<User>() {
                @Override
                public int compare(User user, User t1) {
                    return user.getName().compareTo(t1.getName());
                }
            });
        } else if (direction == -1) {
            Collections.sort(userArrayList, new Comparator<User>() {
                @Override
                public int compare(User user, User t1) {
                    return t1.getName().compareTo(user.getName());
                }
            });
        }
    }

    public void sortByAge(ArrayList<User> userArrayList, int direction) {
        if (direction == 1) {
            Collections.sort(userArrayList, new Comparator<User>() {
                @Override
                public int compare(User user, User t1) {
                    return user.getAge_group().compareTo(t1.getAge_group());
                }
            });
        } else if (direction == -1) {
            Collections.sort(userArrayList, new Comparator<User>() {
                @Override
                public int compare(User user, User t1) {
                    return t1.getAge_group().compareTo(user.getAge_group());
                }
            });
        }
    }

    public void sortByMood(ArrayList<User> userArrayList, int direction) {
        if (direction == 1) {
            Collections.sort(userArrayList, new Comparator<User>() {
                @Override
                public int compare(User user, User t1) {
                    return user.getMood().getTitleOfMood().compareTo(t1.getMood().getTitleOfMood());
                }
            });
        } else if (direction == -1) {
            Collections.sort(userArrayList, new Comparator<User>() {
                @Override
                public int compare(User user, User t1) {
                    return t1.getMood().getTitleOfMood().compareTo(user.getMood().getTitleOfMood());
                }
            });
        }
    }

    public ArrayList<User> filterName(ArrayList<User> list, String start) {
        ArrayList<User> nameFilter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().substring(0, 1).equals(start)) {
                nameFilter.add(list.get(i));
            }
        }

        return nameFilter;
    }

    public ArrayList<User> filterByAge(ArrayList<User> list, String age) {
        ArrayList<User> ageFilter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAge_group().equals(age)) {
                ageFilter.add(list.get(i));
            }
        }
        return ageFilter;
    }

    public ArrayList<User> filterByMood(ArrayList<User> list, int mood) {
        ArrayList<User> ageFilter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMood().getPhoto() == mood) {
                ageFilter.add(list.get(i));
            }
        }

        return ageFilter;
    }

    public void clearSort() {
        for (String key : keySort.keySet()) {
            keySort.get(key).setSet(false);
        }
    }

    public void clearAllFilter() {
        for (String key : keyFilter.keySet()) {
            keyFilter.get(key).setSet(false);
        }
    }

    @Override
    public void closeProfileFragment() {
        getSupportFragmentManager().popBackStack();
    }
}