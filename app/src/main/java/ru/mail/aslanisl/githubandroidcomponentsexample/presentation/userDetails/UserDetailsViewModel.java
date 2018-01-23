package ru.mail.aslanisl.githubandroidcomponentsexample.presentation.userDetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import ru.mail.aslanisl.githubandroidcomponentsexample.App;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.Resource;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserDetailsModel;
import ru.mail.aslanisl.githubandroidcomponentsexample.repositories.UserRepository;

/**
 * Created by Ivan on 22.01.2018.
 */

public class UserDetailsViewModel extends ViewModel {

    @Inject
    UserRepository userRepository;

    private LiveData<Resource<UserDetailsModel>> user;
    private String currentLogin;

    public UserDetailsViewModel() {
        App.getAppComponent().inject(this);
    }

    public void setLogin(String login){
        if (!login.equals(currentLogin)) {
            currentLogin = login;
            user = userRepository.getUser(login);
        }
    }

    public LiveData<Resource<UserDetailsModel>> getUser(){
        return user;
    }
}
