package com.ngh.example.mortartest;

import android.app.Application;

import dagger.ObjectGraph;
import mortar.Mortar;
import mortar.MortarScope;

public class MortarApplication extends Application
{
    private ObjectGraph mObjectGraph;
    private MortarScope mRootScope;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mObjectGraph = ObjectGraph.create(getModules());
        mRootScope = Mortar.createRootScope(BuildConfig.DEBUG, mObjectGraph);
    }

    private Object[] getModules()
    {
        return new Object[]{new AppModule(this)};
    }

    @Override
    public Object getSystemService(String name)
    {
        if (Mortar.isScopeSystemService(name))
        {
            return mRootScope;
        }
        return super.getSystemService(name);
    }
}
