package ru.mail.aslanisl.githubandroidcomponentsexample.models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Ivan on 23.01.2018.
 */
@Dao
public interface UserDetailsDao {

    @Insert(onConflict = REPLACE)
    void insert(UserDetailsModel user);

    @Query("SELECT * FROM user_details_table WHERE login = :login")
    LiveData<UserDetailsModel> load(String login);
}
