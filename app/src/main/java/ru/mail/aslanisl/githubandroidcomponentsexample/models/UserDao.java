package ru.mail.aslanisl.githubandroidcomponentsexample.models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Ivan on 17.01.2018.
 */
@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void insert(UserModel user);

    @Query("SELECT * FROM user_table WHERE login = :login")
    LiveData<UserModel> load(String login);

    @Insert(onConflict = REPLACE)
    void insertAll(List<UserModel> users);

    @Query("SELECT * FROM user_table")
    LiveData<List<UserModel>> loadUsers();

    @Query("SELECT * FROM user_table WHERE id > :fromId LIMIT 30")
    LiveData<List<UserModel>> loadUsers(int fromId);
}
