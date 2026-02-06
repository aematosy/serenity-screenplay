package com.screenplay.project.tasks;

import com.screenplay.project.interactions.EscribirEnCampo;
import com.screenplay.project.userinterface.OpenCartLoginPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class IngresarCredenciales implements Task {

    private final String email;
    private final String password;

    public IngresarCredenciales(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Override
    @Step("{0} ingresa sus credenciales de acceso")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                EscribirEnCampo.conValor(OpenCartLoginPage.INPUT_EMAIL, email),
                EscribirEnCampo.conValor(OpenCartLoginPage.INPUT_PASSWORD, password)
        );
    }

    public static IngresarCredenciales con(String email, String password){
        return Tasks.instrumented(IngresarCredenciales.class, email, password);
    }
}


