package com.ngh.example.mortartest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.ButterKnife;
import flow.Flow;
import mortar.Blueprint;
import mortar.Mortar;

public class ActivityView extends FrameLayout implements CanShowScreen<Blueprint>
{
    @Inject MainBlueprint.Presenter mPresenter;
    private ScreenConductor<Blueprint> mScreenConductor;

    public ActivityView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        Mortar.inject(context, this);
        mScreenConductor = new ScreenConductor<Blueprint>(context, this);
    }

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

    @Override
    public void showScreen(Blueprint newScreen, Blueprint oldScreen, Flow.Direction direction)
    {
        mScreenConductor.showScreen(newScreen, oldScreen, direction);
    }
}
