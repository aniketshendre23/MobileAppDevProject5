package edu.uncc.assignment05;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uncc.assignment05.databinding.FragmentSortBinding;

public class SortFragment extends Fragment {

    public SortFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentSortBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSortBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.imageViewNameAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortFragmentListener.setSortName(1, "NAME (ASC)");
                //sortFragmentListener.setSortNameTextView("NAME (ASC)");
            }
        });

        binding.imageViewNameDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sortFragmentListener.setSortNameTextView("NAME (DESC)");
                sortFragmentListener.setSortName(-1, "NAME (DESC)");
            }
        });

        binding.imageViewAgeAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sortFragmentListener.setSortNameTextView("AGE (ASC)");
                sortFragmentListener.setSortAgeGroup(1, "AGE (ASC)");
            }
        });

        binding.imageViewAgeDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sortFragmentListener.setSortNameTextView("AGE (DESC)");
                sortFragmentListener.setSortAgeGroup(-1, "AGE (DESC)");
            }
        });

        binding.imageViewFeelingAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sortFragmentListener.setSortNameTextView("MOOD (ASC)");
                sortFragmentListener.setSortFeeling(1, "MOOD (ASC)");
            }
        });

        binding.imageViewFeelingDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sortFragmentListener.setSortNameTextView("MOOD (DESC)");
                sortFragmentListener.setSortFeeling(-1, "MOOD (DESC)");
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortFragmentListener.cancelSort();
            }
        });
    }

    SortFragmentListener sortFragmentListener;

    interface SortFragmentListener {
        void setSortName(int direction, String name);

        void setSortAgeGroup(int direction, String name);

        void setSortFeeling(int direction, String name);

        void cancelSort();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        sortFragmentListener = (SortFragmentListener) context;
        super.onAttach(context);
    }
}