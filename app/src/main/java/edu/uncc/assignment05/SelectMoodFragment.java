package edu.uncc.assignment05;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import edu.uncc.assignment05.databinding.FragmentSelectMoodBinding;

public class SelectMoodFragment extends Fragment {

    Mood moodTypes[] = {
            new Mood(R.drawable.not_well, "Not Well"),
            new Mood(R.drawable.sad, "Sad"),
            new Mood(R.drawable.ok, "Ok"),
            new Mood(R.drawable.good, "Good"),
            new Mood(R.drawable.very_good, "Very Good")
    };

    MoodAdapter adapter;

    public SelectMoodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentSelectMoodBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectMoodBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Select Mood");
        adapter = new MoodAdapter(getActivity(), moodTypes);
        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectMoodFragmentListener.addMood(moodTypes[i]);
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectMoodFragmentListener.cancelMoodSelection();
            }
        });
    }

    class MoodAdapter extends ArrayAdapter<Mood> {

        public MoodAdapter(@NonNull Context context, @NonNull Mood[] objects) {
            super(context, R.layout.list_item_mood, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_mood, parent, false);
            }

            ImageView imageViewMood = convertView.findViewById(R.id.imageViewMood);
            TextView textViewMood = convertView.findViewById(R.id.textViewMood);

            imageViewMood.setImageResource(moodTypes[position].getPhoto());
            textViewMood.setText(moodTypes[position].getTitleOfMood());
            return convertView;
        }
    }

    SelectMoodFragmentListener selectMoodFragmentListener;

    interface SelectMoodFragmentListener {
        void addMood(Mood mood);

        void cancelMoodSelection();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        selectMoodFragmentListener = (SelectMoodFragmentListener) context;
        super.onAttach(context);
    }
}