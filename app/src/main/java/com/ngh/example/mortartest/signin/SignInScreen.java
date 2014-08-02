package com.ngh.example.mortartest.signin;

import com.ngh.example.mortartest.MainBlueprint;
import com.ngh.example.mortartest.R;
import com.ngh.example.mortartest.login.LoginScreen;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.Flow;
import flow.Layout;
import mortar.Blueprint;
import mortar.ViewPresenter;

@Layout(R.layout.sign_in_layout)
public class SignInScreen implements Blueprint
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
            addsTo = MainBlueprint.Module.class,
            injects = SignInView.class,
            complete = false
    )
    public static final class Module
    {

    }

    @Singleton
    public static final class Presenter extends ViewPresenter<SignInView>
    {
        private Flow mFlow;

        @Inject
        public Presenter(Flow flow)
        {
            mFlow = flow;
        }

        public void signIn(String email)
        {
            mFlow.goTo(new LoginScreen(email));
        }
    }
}
