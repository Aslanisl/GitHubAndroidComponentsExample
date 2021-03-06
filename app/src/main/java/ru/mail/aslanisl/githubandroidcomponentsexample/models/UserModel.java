package ru.mail.aslanisl.githubandroidcomponentsexample.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan on 17.01.2018.
 */
@Entity(tableName = "user_table")
public class UserModel {

    @SerializedName("login")
    @Expose
    private String login;

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    public UserModel(int id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
