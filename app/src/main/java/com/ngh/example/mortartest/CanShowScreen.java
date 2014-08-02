package com.ngh.example.mortartest;

import flow.Flow;
import mortar.Blueprint;

public interface CanShowScreen<S extends Blueprint>
{
    void showScreen(S newScreen, S oldScreen, Flow.Direction direction);
}
