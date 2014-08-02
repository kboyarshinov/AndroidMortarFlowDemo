package com.ngh.example.mortartest.login;

import android.os.Bundle;

import com.ngh.example.mortartest.MainBlueprint;
import com.ngh.example.mortartest.R;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Provides;
import flow.Layout;
import mortar.Blueprint;
import mortar.ViewPresenter;

@Layout(R.layout.login_layout)
public class LoginScreen implements Blueprint
{
    private final String email;
    public LoginScreen(String email)
    {
        this.email = email;
    }

    @Override
    public String getMortarScopeName()
    {
        return getClass().getName();
    }

    @Override
    public Object getDaggerModule()
    {
        return new Module(email);
    }

    @dagger.Module(
            addsTo = MainBlueprint.Module.class,
            injects = LoginView.class,
            library = true
    )
    public static final class Module
    {
        private final String email;

        public Module(String email)
        {
            this.email = email;
        }

        @Provides
        @Named("Email")
        String provideEmail()
        {
            return email;
        }
    }

    public static final class Presenter extends ViewPresenter<LoginView>
    {
        private final String email;

        @Inject
        public Presenter(@Named("Email") String email)
        {
            this.email = email;
        }

        @Override
        protected void onLoad(Bundle savedInstanceState)
        {
            super.onLoad(savedInstanceState);
            getView().setText(email);
        }
    }
}
