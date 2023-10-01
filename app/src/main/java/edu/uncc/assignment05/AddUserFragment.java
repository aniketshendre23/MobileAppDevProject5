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
import android.widget.Toast;

import edu.uncc.assignment05.databinding.FragmentAddUserBinding;

public class AddUserFragment extends Fragment {

    String ageGroup = null;
    Mood mood = new Mood(-1, "");

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
        Log.d("yooo", "setMood: " + this.mood.toString());
    }

    public AddUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentAddUserBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddUserBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add User");

        if (ageGroup == null) {
            binding.textViewAgeGroup.setText("N/A");
        } else {
            binding.textViewAgeGroup.setText(ageGroup);
        }

        if (mood.getPhoto() == -1) {
            binding.imageViewMood.setImageResource(0);
            binding.textViewMood.setText("N/A");
        } else {
            binding.imageViewMood.setImageResource(mood.getPhoto());
            binding.textViewMood.setText(mood.getTitleOfMood());
        }

        binding.buttonSelectAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserFragmentListener.gotoSelectAgeGroup();
            }
        });

        binding.buttonSelectMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserFragmentListener.gotoSelectMood();
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.editTextText.getText().toString();
                String age = binding.textViewAgeGroup.getText().toString();

                if (name == null || name.length() == 0) {
                    Toast.makeText(getActivity(), "Please enter name.", Toast.LENGTH_SHORT).show();
                } else if (age.equals("N/A") || age == null) {
                    Toast.makeText(getActivity(), "Please select age group.", Toast.LENGTH_SHORT).show();
                } else if (mood.photo == -1) {
                    Toast.makeText(getActivity(), "Please select mood value.", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(name, age, mood);
                    addUserFragmentListener.submit(user);
                }
            }
        });
    }

    AddUserFragmentListener addUserFragmentListener;

    interface AddUserFragmentListener {
        void gotoSelectAgeGroup();

        void gotoSelectMood();

        void submit(User user);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        addUserFragmentListener = (AddUserFragmentListener) context;
        super.onAttach(context);
    }
}