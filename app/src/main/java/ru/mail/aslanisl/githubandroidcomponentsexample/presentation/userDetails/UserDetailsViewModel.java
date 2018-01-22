package ru.mail.aslanisl.githubandroidcomponentsexample.presentation.userDetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ru.mail.aslanisl.githubandroidcomponentsexample.App;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.Resource;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;
import ru.mail.aslanisl.githubandroidcomponentsexample.repositories.UserRepository;

/**
 * Created by Ivan on 22.01.2018.
 */

public class UserDetailsViewModel extends ViewModel {

    @Inject
    UserRepository userRepository;

    private LiveData<Resource<UserModel>> user;

    public UserDetailsViewModel() {
        App.getAppComponent().inject(this);
    }

    public void setLogin(String login){
        user = userRepository.getUser(login);
    }

    public LiveData<Resource<UserModel>> getUser(){
        return user;
    }
}
