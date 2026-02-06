package com.screenplay.project.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class HacerClicEnElemento implements Interaction {

    private final Target elemento;

    public HacerClicEnElemento(Target elemento) {
        this.elemento = elemento;
    }

    @Override
    @Step("{0} hace clic en #elemento")
    public <T extends Actor> void performAs(T actor) {
        //actor.attemptsTo(Click.on(elemento));
        elemento.resolveFor(actor).click();
    }

    public static HacerClicEnElemento en(Target elemento) {
        return instrumented(HacerClicEnElemento.class, elemento);
    }
}
