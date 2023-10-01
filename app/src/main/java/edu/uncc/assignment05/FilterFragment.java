package edu.uncc.assignment05;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import edu.uncc.assignment05.databinding.FragmentFilterBinding;
import edu.uncc.assignment05.databinding.ListItemImageFilterBinding;
import edu.uncc.assignment05.databinding.ListItemMoodBinding;
import edu.uncc.assignment05.databinding.ListItemTextFilterBinding;

public class FilterFragment extends Fragment {

    public static final String ARG_PARAM_LIST = "ARG_PARAM_LIST";

    private ArrayList<User> users = new ArrayList<>();

    private ArrayList<String> nameVal = new ArrayList<>();

    private ArrayList<Integer> moodVal = new ArrayList<>();

    private ArrayList<String> ageVal = new ArrayList<>();

    public FilterFragment() {
        // Required empty public constructor
    }

    public static FilterFragment newInstance(ArrayList<User> users) {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_LIST, users);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            users = (ArrayList<User>) getArguments().getSerializable(ARG_PARAM_LIST);
        }
    }

    FragmentFilterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilterBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Set<Character> firstChars = new HashSet<>();
        Set<Integer> moods = new HashSet<>();
        Set<String> ages = new HashSet<>();
        for (User u : users) {
            firstChars.add(u.getName().charAt(0));
            moods.add(u.getMood().getPhoto());
            ages.add(u.getAge_group());
        }
        for (char ch : firstChars) {
            nameVal.add(String.valueOf(ch));
        }
        for (int m : moods) {
            moodVal.add(m);
        }
        for (String a : ages) {
            ageVal.add(a);
        }

        NameFilterAdapter adapter = new NameFilterAdapter(nameVal, getContext());
        binding.recyclerViewName.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewName.setAdapter(adapter);

        AgeFilterAdapter ageFilterAdapter = new AgeFilterAdapter(ageVal, getContext());
        binding.recyclerViewAge.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewAge.setAdapter(ageFilterAdapter);

        MoodFilterAdapter moodFilterAdapter = new MoodFilterAdapter(moodVal, getContext());
        binding.recyclerViewFeeling.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerViewFeeling.setAdapter(moodFilterAdapter);

        binding.buttonClearFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterFragmentListener.closeFilter();
            }
        });


    }

    FilterFragmentListener filterFragmentListener;

    interface FilterFragmentListener {
        void applyNameFilter(String start, String name);

        void applyAgeFilter(String age, String name);

        void applyMoodFilter(int mood, String name);

        void closeFilter();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        filterFragmentListener = (FilterFragmentListener) context;
        super.onAttach(context);
    }

    class NameFilterAdapter extends RecyclerView.Adapter<NameFilterAdapter.NameFilterHolder> {

        Context context;
        ArrayList<String> nameList;

        public NameFilterAdapter(ArrayList<String> names, Context context) {
            this.nameList = names;
            this.context = context;
        }

        @NonNull
        @Override
        public NameFilterAdapter.NameFilterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemTextFilterBinding binding1 = ListItemTextFilterBinding.inflate(getLayoutInflater(), parent, false);
            return new NameFilterHolder(binding1);
        }

        @Override
        public void onBindViewHolder(@NonNull NameFilterAdapter.NameFilterHolder holder, int position) {
            holder.setupUI(nameList.get(position));
        }

        @Override
        public int getItemCount() {
            return nameList.size();
        }

        public class NameFilterHolder extends RecyclerView.ViewHolder {

            ListItemTextFilterBinding itemTextFilterBinding;

            public NameFilterHolder(ListItemTextFilterBinding binding) {
                super(binding.getRoot());
                itemTextFilterBinding = binding;
            }

            public void setupUI(String name) {
                itemTextFilterBinding.textView.setText(name);

                itemTextFilterBinding.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        filterFragmentListener.applyNameFilter(name, "NAME");
                    }
                });
            }
        }
    }

    class AgeFilterAdapter extends RecyclerView.Adapter<AgeFilterAdapter.AgeFilterHolder> {

        Context context;
        ArrayList<String> ages;

        AgeFilterAdapter(ArrayList<String> ages, Context context) {
            this.ages = ages;
            this.context = context;
        }

        @NonNull
        @Override
        public AgeFilterAdapter.AgeFilterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemTextFilterBinding binding1 = ListItemTextFilterBinding.inflate(getLayoutInflater(), parent, false);
            return new AgeFilterHolder(binding1);
        }

        @Override
        public void onBindViewHolder(@NonNull AgeFilterAdapter.AgeFilterHolder holder, int position) {
            holder.setupUI(ages.get(position));
        }

        @Override
        public int getItemCount() {
            return ages.size();
        }

        public class AgeFilterHolder extends RecyclerView.ViewHolder {

            ListItemTextFilterBinding itemTextFilterBinding;

            public AgeFilterHolder(ListItemTextFilterBinding binding) {
                super(binding.getRoot());
                this.itemTextFilterBinding = binding;
            }

            public void setupUI(String age) {
                itemTextFilterBinding.textView.setText(age);

                itemTextFilterBinding.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        filterFragmentListener.applyAgeFilter(age, "AGE ");
                    }
                });
            }
        }
    }

    class MoodFilterAdapter extends RecyclerView.Adapter<MoodFilterAdapter.MoodFilterHolder> {

        Context context;
        ArrayList<Integer> moods;

        MoodFilterAdapter(ArrayList<Integer> moods, Context context) {
            this.moods = moods;
            this.context = context;
        }

        @NonNull
        @Override
        public MoodFilterAdapter.MoodFilterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemImageFilterBinding binding1 = ListItemImageFilterBinding.inflate(getLayoutInflater(), parent, false);
            return new MoodFilterHolder(binding1);
        }

        @Override
        public void onBindViewHolder(@NonNull MoodFilterAdapter.MoodFilterHolder holder, int position) {
            holder.setupUI(moods.get(position));
        }

        @Override
        public int getItemCount() {
            return moods.size();
        }

        public class MoodFilterHolder extends RecyclerView.ViewHolder {

            ListItemImageFilterBinding listItemImageFilterBinding;

            public MoodFilterHolder(ListItemImageFilterBinding binding) {
                super(binding.getRoot());
                listItemImageFilterBinding = binding;
            }

            public void setupUI(int mood) {
                listItemImageFilterBinding.imageView.setImageResource(mood);

                listItemImageFilterBinding.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        filterFragmentListener.applyMoodFilter(mood, "FEELING");
                    }
                });
            }
        }
    }

}