package com.maurya.fliksearch;

import android.app.Application;

public class FlikSearchApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // dagger will help in mocking the service for testing
        applicationComponent = DaggerApplicationComponent.builder()
                .module(new MovieService.Module())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
