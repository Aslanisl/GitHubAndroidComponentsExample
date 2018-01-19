package ru.mail.aslanisl.githubandroidcomponentsexample.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.mail.aslanisl.githubandroidcomponentsexample.db.UserDatabase;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserDao;
import ru.mail.aslanisl.githubandroidcomponentsexample.repositories.UserRepository;
import ru.mail.aslanisl.githubandroidcomponentsexample.repositories.UserRepositoryImpl;

/**
 * Created by Ivan on 17.01.2018.
 */
@Module
public class RoomModule {

    private UserDatabase userDatabase;

    public RoomModule(Application application){
        userDatabase = Room.databaseBuilder(application, UserDatabase.class, UserDatabase.NAME).build();
    }

    @Singleton
    @Provides
    public UserDatabase providesUserDatabase(){
        return userDatabase;
    }

    @Singleton
    @Provides
    public UserDao providesUserDao(UserDatabase userDatabase){
        return userDatabase.userDao();
    }

    @Singleton
    @Provides
    public UserRepository providesRepository(UserDao userDao){
        return new UserRepositoryImpl(userDao);
    }
}
