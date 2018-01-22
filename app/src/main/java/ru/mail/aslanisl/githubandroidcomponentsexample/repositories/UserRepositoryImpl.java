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
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;
import ru.mail.aslanisl.githubandroidcomponentsexample.utils.AppExecutors;
import ru.mail.aslanisl.githubandroidcomponentsexample.utils.NetworkBoundResource;

/**
 * Created by Ivan on 17.01.2018.
 */
@Singleton
public class UserRepositoryImpl implements UserRepository{

    private UserDao userDao;
    private ApiService apiService;
    private AppExecutors appExecutors;

    @Inject
    public UserRepositoryImpl(UserDao userDao, ApiService apiService, AppExecutors appExecutors) {
        this.userDao = userDao;
        this.apiService = apiService;
        this.appExecutors = appExecutors;
    }

    @Override
    public LiveData<Resource<UserModel>> getUser(final String login) {
        return new NetworkBoundResource<UserModel, UserModel>(appExecutors){
            @Override
            protected void saveCallResult(@NonNull UserModel item) {
                userDao.insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable UserModel data) {
                return data == null || data.getName() == null;
            }

            @NonNull
            @Override
            protected LiveData<UserModel> loadFromDb() {
                return userDao.load(login);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<UserModel>> createCall() {
                return apiService.getUser(login);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<UserModel>>> getUsers(){
        return new NetworkBoundResource<List<UserModel>, List<UserModel>>(appExecutors){
            @Override
            protected void saveCallResult(@NonNull List<UserModel> item) {
                userDao.inserAll(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<UserModel> data) {
                return data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<UserModel>> loadFromDb() {
                return userDao.loadUsers();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<UserModel>>> createCall() {
                return apiService.getUsers();
            }
        }.asLiveData();
    }
}
