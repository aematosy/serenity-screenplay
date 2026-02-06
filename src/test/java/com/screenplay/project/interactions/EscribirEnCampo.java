package com.screenplay.project.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EscribirEnCampo implements Interaction {

    private final Target campo;
    private final String valor;

    public EscribirEnCampo(Target campo, String valor) {
        this.campo = campo;
        this.valor = valor;
    }

    @Override
    @Step("{0} escribe '#valor' en #campo")
    public <T extends Actor> void performAs(T actor) {
        WebElement input = campo.resolveFor(actor);
        input.clear();
        input.sendKeys(valor);
    }

    public static EscribirEnCampo conValor(Target campo, String valor) {
        return instrumented(EscribirEnCampo.class, campo, valor);
    }
}
