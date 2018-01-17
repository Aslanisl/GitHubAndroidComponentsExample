package ru.mail.aslanisl.githubandroidcomponentsexample.repositories;

import android.arch.lifecycle.LiveData;

import java.util.List;

import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;

/**
 * Created by Ivan on 17.01.2018.
 */

public interface UserRepository {

    void insertUser(UserModel user);

    void insertUsers(List<UserModel> users);

    LiveData<UserModel> getUser(int userId);

    LiveData<List<UserModel>> getUsers();
}
