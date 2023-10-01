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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.uncc.assignment05.databinding.FragmentUsersBinding;
import edu.uncc.assignment05.databinding.ListItemUserBinding;

public class UsersFragment extends Fragment {
    ArrayList<User> usersList = new ArrayList<>();

    String sortName = "No Sort";
    String filterName = "No Filter";

    private UserItemListAdapter userItemListAdapter;
    RecyclerView recyclerView;

    public UsersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentUsersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Users");
        binding.textViewSort.setText(sortName);
        binding.textViewFilter.setText(filterName);

        binding.buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usersFragmentListener.gotoAddUser();
            }
        });

        binding.buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usersFragmentListener.clear();
                userItemListAdapter.notifyDataSetChanged();
            }
        });

        binding.imageViewSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usersFragmentListener.gotoSort();
            }
        });

        binding.imageViewFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usersFragmentListener.gotoFilter();
            }
        });


        if (usersList.size() > 0) {
            recyclerView = binding.recyclerView;
            userItemListAdapter = new UserItemListAdapter(usersList, getContext());
            recyclerView.setAdapter(userItemListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

    }

    public void dataSetChanged() {
        userItemListAdapter.notifyDataSetChanged();
    }

    public void setSortName(String name) {
        this.sortName = name;
    }

    public void setFilterName(String name) {
        this.filterName = name;
    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
    }

    UsersFragmentListener usersFragmentListener;

    interface UsersFragmentListener {
        void gotoAddUser();

        void clear();

        void removeUser(User user);

        void gotoSort();

        void gotoFilter();

        void gotoProfile(User user);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        usersFragmentListener = (UsersFragmentListener) context;
        super.onAttach(context);
    }

    class UserItemListAdapter extends RecyclerView.Adapter<UserItemListAdapter.UserHolder> {

        Context context;
        ArrayList<User> users;

        public UserItemListAdapter(ArrayList<User> users, Context context) {
            this.context = context;
            this.users = users;
        }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemUserBinding binding1 = ListItemUserBinding.inflate(getLayoutInflater(), parent, false);
            return new UserHolder(binding1);
        }

        @Override
        public void onBindViewHolder(@NonNull UserHolder holder, int position) {
            User user = users.get(position);
            holder.setupUI(user);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        public class UserHolder extends RecyclerView.ViewHolder {

            ListItemUserBinding userBinding;

            public UserHolder(ListItemUserBinding binding) {
                super(binding.getRoot());
                userBinding = binding;
            }

            public void setupUI(User user) {
                userBinding.imageViewUserMood.setImageResource(user.getMood().getPhoto());
                userBinding.textViewUserAgeGroup.setText(user.getAge_group());
                userBinding.textViewUserName.setText(user.getName());

                userBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        usersList.remove(user);
                        usersFragmentListener.removeUser(user);
                        notifyDataSetChanged();
                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        usersFragmentListener.gotoProfile(user);
                    }
                });
            }
        }
    }
}

