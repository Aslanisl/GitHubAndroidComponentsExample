package ru.mail.aslanisl.githubandroidcomponentsexample.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan on 23.01.2018.
 */
@Entity(tableName = "user_details_table")
public class UserDetailsModel {

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

    @SerializedName("name")
    @Expose
    private String name;

    public UserDetailsModel(String login, int id, String avatarUrl, String name) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
