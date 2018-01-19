package ru.mail.aslanisl.githubandroidcomponentsexample.data.network;

import android.arch.lifecycle.LiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;

/**
 * Created by Ivan on 17.01.2018.
 */

public interface RestApi {

    @GET("/users")
    Call<LiveData<List<UserModel>>> getUsers();

    @GET("/users/{username}")
    Call<LiveData<UserModel>> getUser(@Path("username") String login);
}
