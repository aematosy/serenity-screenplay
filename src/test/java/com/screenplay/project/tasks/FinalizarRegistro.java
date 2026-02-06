package com.screenplay.project.tasks;

import com.screenplay.project.interactions.HacerClicEnElemento;
import com.screenplay.project.userinterface.RegistroUsuarioPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class FinalizarRegistro implements Task {
    @Override
    @Step("{0} confirma el registro del usuario")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                HacerClicEnElemento.en(RegistroUsuarioPage.BOTON_CONTINUAR)
        );
    }

    public static FinalizarRegistro enElFormulario() {
        return Tasks.instrumented(FinalizarRegistro.class);
    }
}

