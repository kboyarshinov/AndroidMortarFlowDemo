package com.ngh.example.mortartest;

import android.app.Application;
import android.content.Context;

import com.ngh.example.mortartest.signin.SignInScreen;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Provides;
import flow.Flow;
import flow.Parcer;
import mortar.Blueprint;

public class MainBlueprint implements Blueprint
{
    @Override
    public String getMortarScopeName()
    {
        return getClass().getName();
    }

    @Override
    public Object getDaggerModule()
    {
        return new Module();
    }

    @dagger.Module(
            addsTo = AppModule.class,
            injects = ActivityView.class,
            includes = GsonParcerModule.class,
            library = true
    )
    public static final class Module
    {
        @Provides
        @Singleton
        public Context provideContext(Application application)
        {
            return application;
        }

        @Provides
        @Singleton
        @Named("MortarString")
        public String provideString()
        {
            return "Hello, Mortar!";
        }

        @Provides
        @Singleton
        Flow provideFlow(Presenter presenter)
        {
            return presenter.getFlow();
        }
    }

    @Singleton
    public static final class Presenter extends FlowPresenter<Blueprint, ActivityView>
    {
        @Inject
        public Presenter(Parcer<Object> parcer)
        {
            super(parcer);
        }

        @Override
        protected Blueprint getFirstScreen()
        {
            return new SignInScreen();
        }
    }
}
