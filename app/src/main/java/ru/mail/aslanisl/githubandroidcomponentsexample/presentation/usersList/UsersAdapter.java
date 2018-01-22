package ru.mail.aslanisl.githubandroidcomponentsexample.presentation.usersList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mail.aslanisl.githubandroidcomponentsexample.R;
import ru.mail.aslanisl.githubandroidcomponentsexample.models.UserModel;
import ru.mail.aslanisl.githubandroidcomponentsexample.utils.GlideApp;

/**
 * Created by Ivan on 22.01.2018.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>{

    private List<UserModel> userModels;
    private Context context;

    public UsersAdapter(List<UserModel> userModels, Context context) {
        this.userModels = userModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position < userModels.size()){
            UserModel user = userModels.get(position);
            if (user != null) holder.bindHolder(user);
        }
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_user_avatar) ImageView avatar;
        @BindView(R.id.item_user_name) TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindHolder(UserModel user){
            GlideApp.with(context).load(user.getAvatarUrl()).into(avatar);
            name.setText(user.getName() != null ? user.getName() : "No name");
        }
    }
}