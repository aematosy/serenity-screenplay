package com.screenplay.project.tasks;

import com.screenplay.project.userinterface.RegistroUsuarioPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class FinalizarRegistro implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(RegistroUsuarioPage.BOTON_CONTINUAR) // Hace clic en el botón continuar

        );
    }

    public static FinalizarRegistro enElFormulario() {
        return Tasks.instrumented(FinalizarRegistro.class);
    }
}
