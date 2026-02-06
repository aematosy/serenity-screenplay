package com.screenplay.project.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EsperarElemento implements Interaction {

    private final Target elemento;
    private final int segundos;
    private final String condicion; // "VISIBLE", "PRESENTE", "CLICKABLE"

    public EsperarElemento(Target elemento, int segundos, String condicion) {
        this.elemento = elemento;
        this.segundos = segundos;
        this.condicion = condicion;
    }

    @Override
    @Step("{0} espera que #elemento esté #condicion por #segundos segundos")
    public <T extends Actor> void performAs(T actor) {
        switch (condicion.toUpperCase()) {
            case "PRESENTE":
                actor.attemptsTo(WaitUntil.the(elemento, WebElementStateMatchers.isPresent()).forNoMoreThan(segundos).seconds());
                break;
            case "CLICKABLE":
                actor.attemptsTo(WaitUntil.the(elemento, WebElementStateMatchers.isClickable()).forNoMoreThan(segundos).seconds());
                break;
            default: // VISIBLE
                actor.attemptsTo(WaitUntil.the(elemento, WebElementStateMatchers.isVisible()).forNoMoreThan(segundos).seconds());
        }
    }

    public static EsperarElemento visible(Target elemento, int segundos) {
        return instrumented(EsperarElemento.class, elemento, segundos, "VISIBLE");
    }

    public static EsperarElemento presente(Target elemento, int segundos) {
        return instrumented(EsperarElemento.class, elemento, segundos, "PRESENTE");
    }

    public static EsperarElemento clickable(Target elemento, int segundos) {
        return instrumented(EsperarElemento.class, elemento, segundos, "CLICKABLE");
    }
}

