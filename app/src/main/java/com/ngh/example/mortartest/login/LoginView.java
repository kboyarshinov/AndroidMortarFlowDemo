package com.ngh.example.mortartest.login;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ngh.example.mortartest.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mortar.Mortar;

public class LoginView extends FrameLayout
{
    @Inject LoginScreen.Presenter mPresenter;

    public LoginView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        Mortar.inject(context, this);
    }

    @InjectView(R.id.login_success_text)
    TextView mTextView;

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        ButterKnife.inject(this);
        mPresenter.takeView(this);
    }

    public void setText(String text)
    {
        mTextView.setText(text);
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mPresenter.dropView(this);
    }
}
