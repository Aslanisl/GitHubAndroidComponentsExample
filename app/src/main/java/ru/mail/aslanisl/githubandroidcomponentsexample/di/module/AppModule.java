package ru.mail.aslanisl.githubandroidcomponentsexample.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.mail.aslanisl.githubandroidcomponentsexample.App;

/**
 * Created by Ivan on 17.01.2018.
 */
@Module
public class AppModule {

    private App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public App providesApplication(){
        return application;
    }
}
