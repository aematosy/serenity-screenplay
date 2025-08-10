package com.screenplay.project.stepdefinitions.hook;

import com.screenplay.project.hook.OpenWeb;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import org.hamcrest.Matchers;

import static com.screenplay.project.utils.Constants.TIME_SHORT;
import static com.screenplay.project.utils.Constants.TITTLE;
import static com.screenplay.project.utils.Time.waiting;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class Hook {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("{string} ingresa a la pagina de open cart")
    public void ingresa_a_la_pagina_de_open_cart(String actor) {
        OnStage.theActorCalled(actor).attemptsTo(OpenWeb.browserURL());
        waiting(TIME_SHORT);
        theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(TheWebPage.title(), Matchers.containsString(TITTLE))
        );
    }
}
