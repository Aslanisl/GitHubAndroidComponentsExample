package ru.mail.aslanisl.githubandroidcomponentsexample.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mail.aslanisl.githubandroidcomponentsexample.BuildConfig;
import ru.mail.aslanisl.githubandroidcomponentsexample.api.ApiService;
import ru.mail.aslanisl.githubandroidcomponentsexample.db.UserDatabase;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserDao;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserDetailsDao;
import ru.mail.aslanisl.githubandroidcomponentsexample.repositories.UserRepository;
import ru.mail.aslanisl.githubandroidcomponentsexample.repositories.UserRepositoryImpl;
import ru.mail.aslanisl.githubandroidcomponentsexample.utils.AppExecutors;
import ru.mail.aslanisl.githubandroidcomponentsexample.utils.LiveDataCallAdapterFactory;

/**
 * Created by Ivan on 17.01.2018.
 */
@Module
public class UserModule {

    private Application application;

    public UserModule(Application application){
        this.application = application;
    }

    @Singleton
    @Provides
    public UserDatabase providesUserDatabase(){
        return Room.databaseBuilder(application, UserDatabase.class, UserDatabase.NAME).build();
    }

    @Singleton
    @Provides
    public UserDao providesUserDao(UserDatabase userDatabase){
        return userDatabase.userDao();
    }

    @Singleton
    @Provides
    public UserDetailsDao providesUserDetailsDao(UserDatabase userDatabase) {
        return userDatabase.userDetailsDao();
    }

    @Singleton
    @Provides
    public UserRepository providesRepository(UserDao userDao, UserDetailsDao userDetailsDao, ApiService apiService, AppExecutors appExecutors){
        return new UserRepositoryImpl(userDao, userDetailsDao, apiService, appExecutors);
    }

    @Singleton
    @Provides
    public AppExecutors providesAppExecutors(){
        return new AppExecutors();
    }

    @Singleton
    @Provides
    public ApiService provideApiService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) builder.addInterceptor(interceptor);
        builder.retryOnConnectionFailure(true);
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(client)
                .build()
                .create(ApiService.class);
    }
}
