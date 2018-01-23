package ru.mail.aslanisl.githubandroidcomponentsexample.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserDao;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserDetailsDao;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserDetailsModel;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;

/**
 * Created by Ivan on 17.01.2018.
 */
@Database(entities = {UserModel.class, UserDetailsModel.class}, version = UserDatabase.VERSION)
public abstract class UserDatabase extends RoomDatabase {
    static final int VERSION = 1;
    public static final String NAME = "user-db";

    public abstract UserDao userDao();
    public abstract UserDetailsDao userDetailsDao();
}
