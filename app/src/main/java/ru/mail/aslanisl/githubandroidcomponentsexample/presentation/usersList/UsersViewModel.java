package ru.mail.aslanisl.githubandroidcomponentsexample.presentation.usersList;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.mail.aslanisl.githubandroidcomponentsexample.App;
import ru.mail.aslanisl.githubandroidcomponentsexample.api.ApiResponse;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.Resource;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.Status;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;
import ru.mail.aslanisl.githubandroidcomponentsexample.repositories.UserRepository;

/**
 * Created by Ivan on 17.01.2018.
 */

public class UsersViewModel extends ViewModel {

    private MediatorLiveData<Resource<List<UserModel>>> users = new MediatorLiveData<>();
    private List<UserModel> usersList = new ArrayList<>();

    @Inject
    UserRepository userRepository;

    public UsersViewModel(){
        App.getAppComponent().inject(this);
    }

    public LiveData<Resource<List<UserModel>>> getUsers(){
        if (usersList.isEmpty()){
            // Loading from id 0
            LiveData<Resource<List<UserModel>>> usersResponse = userRepository.getUsers(0);
            users.addSource(usersResponse, listResource -> {
                if (listResource != null
                        && (listResource.getStatus() == Status.SUCCESS || listResource.getStatus() == Status.ERROR)) {
                    users.removeSource(usersResponse);
                    if (listResource.getData() != null && !listResource.getData().isEmpty()){
                        usersList.clear();
                        usersList.addAll(listResource.getData());
                    }
                }
                users.setValue(listResource);
            });
        }
        return users;
    }

    public LiveData<Resource<List<UserModel>>> loadMore(){
        int lastId = 0;
        if (!usersList.isEmpty()){
            lastId = usersList.get(usersList.size() - 1).getId();
        }
        LiveData<Resource<List<UserModel>>> usersResponse = userRepository.getUsers(lastId);
        users.addSource(usersResponse, listResource -> {
            if (listResource != null
                    && (listResource.getStatus() == Status.SUCCESS || listResource.getStatus() == Status.ERROR)) {
                users.removeSource(usersResponse);
                if (listResource.getData() != null && !listResource.getData().isEmpty()){
                    usersList.addAll(listResource.getData());
                    users.setValue(Resource.success(usersList));
                }
            }
        });
        return usersResponse;
    }
}
