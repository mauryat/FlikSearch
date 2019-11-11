package com.maurya.fliksearch;

import dagger.Component;

@Component(modules = {MovieService.Module.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}
