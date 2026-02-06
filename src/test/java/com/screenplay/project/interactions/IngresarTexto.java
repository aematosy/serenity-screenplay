package com.screenplay.project.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class IngresarTexto implements Interaction {

    private final String valor;
    private final Target campo;

    public IngresarTexto(String valor, Target campo) {
        this.valor = valor;
        this.campo = campo;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(valor).into(campo));
    }

    public static IngresarTexto enElCampo(Target campo, String valor) {
        return instrumented(IngresarTexto.class, valor, campo);
    }
}
