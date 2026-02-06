package com.screenplay.project.tasks;

import com.screenplay.project.interactions.SeleccionarOpcionDeMiCuenta;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Tarea Screenplay: ingresar al login desde el menú "Mi Cuenta".
 */
public class AbrirLoginDesdeMiCuenta implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SeleccionarOpcionDeMiCuenta.login());
    }

    public static AbrirLoginDesdeMiCuenta desdeMiCuenta() {
        return instrumented(AbrirLoginDesdeMiCuenta.class);
    }
}
