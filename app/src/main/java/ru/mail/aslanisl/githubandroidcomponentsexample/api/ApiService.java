package ru.mail.aslanisl.githubandroidcomponentsexample.api;

import android.arch.lifecycle.LiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserDetailsModel;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;

/**
 * Created by Ivan on 17.01.2018.
 */

public interface ApiService {

    @GET("/users")
    LiveData<ApiResponse<List<UserModel>>> getUsers(@Query("since") int fromId);

    @GET("/users/{username}")
    LiveData<ApiResponse<UserDetailsModel>> getUser(@Path("username") String login);
}
