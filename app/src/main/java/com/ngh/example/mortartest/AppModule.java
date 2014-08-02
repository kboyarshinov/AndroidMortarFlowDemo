package com.ngh.example.mortartest;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = MortarApplication.class,
        complete = false,
        library = true
)
public class AppModule
{
    private MortarApplication mMortarApplication;

    public AppModule(MortarApplication mortarApplication)
    {
        mMortarApplication = mortarApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication()
    {
        return mMortarApplication;
    }
}
