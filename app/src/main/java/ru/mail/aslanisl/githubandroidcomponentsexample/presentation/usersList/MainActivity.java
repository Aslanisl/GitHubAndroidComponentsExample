package ru.mail.aslanisl.githubandroidcomponentsexample.presentation.usersList;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import ru.mail.aslanisl.githubandroidcomponentsexample.R;

public class MainActivity extends AppCompatActivity {

    private UsersViewModel usersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        subscribe();
    }

    private void subscribe(){
        usersViewModel.getUsers().observe(this, userModels -> {
            if (userModels != null){
                Toast.makeText(this, TextUtils.join(", ", userModels), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
