package com.screenplay.project.tasks;

import com.screenplay.project.userinterface.OpenCartHomePage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Tarea Screenplay que abre la aplicación OpenCart.
 */
public class OpenWeb implements Task {

    @Override
    @Step("{0} abre la aplicación OpenCart")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.browserOn().the(OpenCartHomePage.class));
    }

    public static OpenWeb abrirAplicacion() {
        return instrumented(OpenWeb.class);
    }
}
