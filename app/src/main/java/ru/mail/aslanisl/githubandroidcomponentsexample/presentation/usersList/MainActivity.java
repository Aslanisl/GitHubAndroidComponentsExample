package ru.mail.aslanisl.githubandroidcomponentsexample.presentation.usersList;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mail.aslanisl.githubandroidcomponentsexample.R;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.Resource;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.Status;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;
import ru.mail.aslanisl.githubandroidcomponentsexample.presentation.userDetails.UserDetailsActivity;
import ru.mail.aslanisl.githubandroidcomponentsexample.utils.EndlessRecyclerOnScrollListener;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.users_loading) ProgressBar usersLoading;
    @BindView(R.id.users_recycler) RecyclerView usersRecycler;
    private UsersAdapter usersAdapter;
    private UsersViewModel usersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        subscribe();
    }

    private void subscribe(){
        Observer<Resource<List<UserModel>>> observer = new Observer<Resource<List<UserModel>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<UserModel>> userModels) {
                if (userModels != null){
                    if (userModels.getStatus() == Status.LOADING){
                        usersLoading.setVisibility(View.VISIBLE);
                        usersRecycler.setVisibility(View.GONE);
                    } else if (userModels.getStatus() == Status.SUCCESS && userModels.getData() != null){
                        usersLoading.setVisibility(View.GONE);
                        usersRecycler.setVisibility(View.VISIBLE);

                        if (usersAdapter == null) {
                            usersAdapter = new UsersAdapter();
                            usersRecycler.setAdapter(usersAdapter);
                            usersAdapter.updateUsers(userModels.getData());
                            usersRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            usersRecycler.addOnScrollListener(new EndlessRecyclerOnScrollListener(MainActivity.this::loadMore));
                            usersAdapter.setUserListener(login -> {
                                if (login != null) {
                                    Intent intent = new Intent(MainActivity.this, UserDetailsActivity.class);
                                    intent.putExtra(UserDetailsActivity.KEY_LOGIN, login);
                                    startActivity(intent);
                                }
                            });

                        }
                        usersViewModel.getUsers().removeObserver(this);
                    }
                }
            }
        };

        usersViewModel.getUsers().observe(this, observer);
    }

    private void loadMore(){
        usersViewModel.loadMore().observe(this, userModels -> {
            if (userModels != null && userModels.getData() != null) {
                usersAdapter.updateUsers(userModels.getData());
            }
        });
    }
}
