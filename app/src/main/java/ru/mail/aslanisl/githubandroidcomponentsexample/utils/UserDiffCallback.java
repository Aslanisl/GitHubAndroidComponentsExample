package ru.mail.aslanisl.githubandroidcomponentsexample.utils;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;

/**
 * Created by Ivan on 23.01.2018.
 */

public class UserDiffCallback extends DiffUtil.Callback{

    private List<UserModel> oldValues;
    private List<UserModel> newValues;

    public UserDiffCallback(List<UserModel> newValues, List<UserModel> oldValues) {
        this.newValues = newValues;
        this.oldValues = oldValues;
    }

    @Override
    public int getOldListSize() {
        return oldValues.size();
    }

    @Override
    public int getNewListSize() {
        return newValues.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldValues.get(oldItemPosition).getId() == newValues.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        UserModel oldValue = oldValues.get(oldItemPosition);
        UserModel newValue = newValues.get(newItemPosition);
        return oldValue.getLogin().equals(newValue.getLogin()) && oldValue.getId() == newValue.getId();
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}