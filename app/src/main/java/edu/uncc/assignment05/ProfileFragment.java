package edu.uncc.assignment05;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uncc.assignment05.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private static final String ARG_PARAM_USER = "USER";

    private User pUser;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(User user) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pUser = (User) getArguments().getSerializable(ARG_PARAM_USER);
        }
    }

    FragmentProfileBinding fragmentProfileBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return fragmentProfileBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");

        fragmentProfileBinding.imageViewMood.setImageResource(pUser.getMood().getPhoto());
        fragmentProfileBinding.textViewUserMood.setText(pUser.getMood().getTitleOfMood());
        fragmentProfileBinding.textViewUserName.setText(pUser.getName());
        fragmentProfileBinding.textViewUserAgeGroup.setText(pUser.getAge_group());

        fragmentProfileBinding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileFragmentListener.closeProfileFragment();
            }
        });
    }

    ProfileFragmentListener profileFragmentListener;
    interface ProfileFragmentListener {
        void closeProfileFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        profileFragmentListener = (ProfileFragmentListener) context;
        super.onAttach(context);
    }
}