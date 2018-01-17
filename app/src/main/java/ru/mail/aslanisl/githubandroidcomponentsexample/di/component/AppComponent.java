package ru.mail.aslanisl.githubandroidcomponentsexample.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.mail.aslanisl.githubandroidcomponentsexample.di.module.AppModule;
import ru.mail.aslanisl.githubandroidcomponentsexample.di.module.RoomModule;
import ru.mail.aslanisl.githubandroidcomponentsexample.presentation.usersList.UsersViewModel;

/**
 * Created by Ivan on 17.01.2018.
 */
@Singleton
@Component(modules = {AppModule.class, RoomModule.class})
public interface AppComponent {
    void inject(UsersViewModel usersViewModel);
}
