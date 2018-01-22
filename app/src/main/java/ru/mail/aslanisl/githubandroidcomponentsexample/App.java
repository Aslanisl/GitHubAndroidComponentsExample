package ru.mail.aslanisl.githubandroidcomponentsexample;

import android.app.Application;

import ru.mail.aslanisl.githubandroidcomponentsexample.di.component.AppComponent;
import ru.mail.aslanisl.githubandroidcomponentsexample.di.component.DaggerAppComponent;
import ru.mail.aslanisl.githubandroidcomponentsexample.di.module.UserModule;

/**
 * Created by Ivan on 17.01.2018.
 */

public class App extends Application {

    private static AppComponent appComponent;

    public App(){

        appComponent = DaggerAppComponent.builder()
                .userModule(new UserModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
