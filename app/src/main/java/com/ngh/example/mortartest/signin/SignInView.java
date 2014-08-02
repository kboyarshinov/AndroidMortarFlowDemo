package com.ngh.example.mortartest.signin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;

import com.ngh.example.mortartest.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import mortar.Mortar;

public class SignInView extends FrameLayout
{
    @Inject SignInScreen.Presenter mPresenter;
    public SignInView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        Mortar.inject(context, this);
    }

    @InjectView(R.id.login_form)
    View mView;

    @InjectView(R.id.email)
    AutoCompleteTextView mAutoCompleteTextView;

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        ButterKnife.inject(this);
        mPresenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mPresenter.dropView(this);
    }

    @OnClick(R.id.email_sign_in_button)
    public void signIn()
    {
        mPresenter.signIn(mAutoCompleteTextView.getText().toString());
    }

}
