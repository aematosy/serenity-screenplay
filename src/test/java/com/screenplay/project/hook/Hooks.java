package com.screenplay.project.hook;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class Hooks {

    @Before
    public void beforeScenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void afterScenario() {
        OnStage.drawTheCurtain();
    }

}

