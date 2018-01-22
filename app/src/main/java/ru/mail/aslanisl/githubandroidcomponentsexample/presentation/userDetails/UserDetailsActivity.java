package ru.mail.aslanisl.githubandroidcomponentsexample.presentation.userDetails;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mail.aslanisl.githubandroidcomponentsexample.R;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.Status;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;
import ru.mail.aslanisl.githubandroidcomponentsexample.utils.GlideApp;

public class UserDetailsActivity extends AppCompatActivity {
    public static final String KEY_LOGIN = "login";

    private UserDetailsViewModel userDetailsViewModel;

    @BindView(R.id.user_loading) ProgressBar progressBar;
    @BindView(R.id.user_avatar) ImageView avatar;
    @BindView(R.id.user_name) TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);

        userDetailsViewModel = ViewModelProviders.of(this).get(UserDetailsViewModel.class);
        Intent intent = getIntent();
        if (intent != null) {
            String login = intent.getStringExtra(KEY_LOGIN);
            if (login != null) subscribe(login);
        }
    }

    private void subscribe(String login){
        userDetailsViewModel.setLogin(login);
        userDetailsViewModel.getUser().observe(this, resource -> {
            if (resource != null) {
                if (resource.getStatus() == Status.LOADING) {
                    progressBar.setVisibility(View.VISIBLE);
                    avatar.setVisibility(View.GONE);
                    name.setVisibility(View.GONE);
                } else if (resource.getStatus() == Status.SUCCESS && resource.getData() != null){
                    progressBar.setVisibility(View.GONE);
                    avatar.setVisibility(View.VISIBLE);
                    name.setVisibility(View.VISIBLE);
                    UserModel user = resource.getData();
                    GlideApp.with(this).load(user.getAvatarUrl()).into(avatar);
                    name.setText(user.getName() != null ? user.getName() : user.getLogin());
                }
            }
        });
    }
}
