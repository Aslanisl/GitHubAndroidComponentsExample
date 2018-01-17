package ru.mail.aslanisl.githubandroidcomponentsexample.repositories;

import android.arch.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserDao;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;

/**
 * Created by Ivan on 17.01.2018.
 */
@Singleton
public class UserRepositoryImpl implements UserRepository{

    private UserDao userDao;

    @Inject
    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void insertUser(UserModel user) {

    }

    @Override
    public void insertUsers(List<UserModel> users) {

    }

    @Override
    public LiveData<UserModel> getUser(int userId) {
        return null;
    }

    @Override
    public LiveData<List<UserModel>> getUsers(){
        return userDao.loadUsers();
    }
}
