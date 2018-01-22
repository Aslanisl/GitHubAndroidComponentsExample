package ru.mail.aslanisl.githubandroidcomponentsexample.repositories;

import android.arch.lifecycle.LiveData;

import java.util.List;

import ru.mail.aslanisl.githubandroidcomponentsexample.models.Resource;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;

/**
 * Created by Ivan on 17.01.2018.
 */

public interface UserRepository {

    LiveData<Resource<UserModel>> getUser(String login);

    LiveData<Resource<List<UserModel>>> getUsers();
}
