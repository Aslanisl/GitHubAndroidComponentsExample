package ru.mail.aslanisl.githubandroidcomponentsexample.repositories;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.mail.aslanisl.githubandroidcomponentsexample.api.ApiResponse;
import ru.mail.aslanisl.githubandroidcomponentsexample.api.ApiService;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.Resource;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserDao;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserDetailsDao;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserDetailsModel;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;
import ru.mail.aslanisl.githubandroidcomponentsexample.utils.AppExecutors;
import ru.mail.aslanisl.githubandroidcomponentsexample.utils.NetworkBoundResource;

/**
 * Created by Ivan on 17.01.2018.
 */
@Singleton
public class UserRepositoryImpl implements UserRepository{

    private UserDao userDao;
    private UserDetailsDao userDetailsDao;
    private ApiService apiService;
    private AppExecutors appExecutors;

    @Inject
    public UserRepositoryImpl(UserDao userDao, UserDetailsDao userDetailsDao, ApiService apiService, AppExecutors appExecutors) {
        this.userDao = userDao;
        this.userDetailsDao = userDetailsDao;
        this.apiService = apiService;
        this.appExecutors = appExecutors;
    }

    @Override
    public LiveData<Resource<UserDetailsModel>> getUser(final String login) {
        return new NetworkBoundResource<UserDetailsModel, UserDetailsModel>(appExecutors){
            @Override
            protected void saveCallResult(@NonNull UserDetailsModel item) {
                userDetailsDao.insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable UserDetailsModel data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<UserDetailsModel> loadFromDb() {
                return userDetailsDao.load(login);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<UserDetailsModel>> createCall() {
                return apiService.getUser(login);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<UserModel>>> getUsers(int fromId){
        return new NetworkBoundResource<List<UserModel>, List<UserModel>>(appExecutors){
            @Override
            protected void saveCallResult(@NonNull List<UserModel> item) {
                userDao.insertAll(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<UserModel> data) {
                return data == null || data.isEmpty() || data.get(data.size() - 1).getId() <= fromId;
            }

            @NonNull
            @Override
            protected LiveData<List<UserModel>> loadFromDb() {
                return userDao.loadUsers(fromId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<UserModel>>> createCall() {
                return apiService.getUsers(fromId);
            }
        }.asLiveData();
    }
}
