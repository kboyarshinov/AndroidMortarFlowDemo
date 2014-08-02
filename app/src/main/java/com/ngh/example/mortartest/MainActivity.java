package com.ngh.example.mortartest;

import android.app.Activity;
import android.os.Bundle;

import mortar.Blueprint;
import mortar.Mortar;
import mortar.MortarActivityScope;
import mortar.MortarScope;


/**
 * A base class to wrap communication with the Google Play Services PlusClient.
 */
public class MainActivity extends Activity
{
    private static final String TAG = MainActivity.class.getSimpleName();

    private MortarActivityScope activityScope;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        MortarScope parentScope = Mortar.getScope(getApplication());
        activityScope = Mortar.requireActivityScope(parentScope, getBlueprint());
        activityScope.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Blueprint getBlueprint()
    {
        return new MainBlueprint();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        activityScope.onSaveInstanceState(outState);
    }

    @Override
    public Object getSystemService(String name)
    {
        if (Mortar.isScopeSystemService(name))
        {
            return activityScope;
        }
        return super.getSystemService(name);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (isFinishing())
        {
            MortarScope parentScope = Mortar.getScope(getApplication());
            parentScope.destroyChild(activityScope);
            activityScope = null;
        }
    }
}
