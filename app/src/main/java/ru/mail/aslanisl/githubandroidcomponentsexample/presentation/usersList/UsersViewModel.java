package ru.mail.aslanisl.githubandroidcomponentsexample.presentation.usersList;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ru.mail.aslanisl.githubandroidcomponentsexample.App;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;
import ru.mail.aslanisl.githubandroidcomponentsexample.repositories.UserRepository;

/**
 * Created by Ivan on 17.01.2018.
 */

public class UsersViewModel extends ViewModel {

    private MutableLiveData<List<UserModel>> users;

    @Inject
    private UserRepository mUserRepository;

    public UsersViewModel(){
        App.getAppComponent().inject(this);
    }

    public LiveData<List<UserModel>> getUsers(){
        return mUserRepository.getUsers();
    }
}
