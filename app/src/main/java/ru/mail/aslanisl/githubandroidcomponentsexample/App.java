package ru.mail.aslanisl.githubandroidcomponentsexample;

import android.app.Application;

import ru.mail.aslanisl.githubandroidcomponentsexample.di.component.AppComponent;
import ru.mail.aslanisl.githubandroidcomponentsexample.di.module.AppModule;
import ru.mail.aslanisl.githubandroidcomponentsexample.di.module.RoomModule;

/**
 * Created by Ivan on 17.01.2018.
 */

public class App extends Application {

    private static AppComponent appComponent;

    public App(){

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .appModule(new RoomModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
