package com.screenplay.project.interactions;

import com.screenplay.project.userinterface.OpenCartHomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Interacción que hace clic en el botón "Mi Cuenta" y luego en el enlace de Login.
 */
public class SeleccionarOpcionDeMiCuenta implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        // 1. Hacer clic en el botón "Mi Cuenta"
        actor.attemptsTo(
                Click.on(OpenCartHomePage.BOTON_MI_CUENTA)
        );

        // 2. Hacer clic en el enlace de Login
        actor.attemptsTo(
                Click.on(OpenCartHomePage.ENLACE_LOGIN)
        );
    }

    /**
     * Método factory para crear la interacción
     */
    public static SeleccionarOpcionDeMiCuenta login() {
        return instrumented(SeleccionarOpcionDeMiCuenta.class);
    }
}