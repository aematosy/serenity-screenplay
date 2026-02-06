package com.screenplay.project.tasks;

import com.screenplay.project.interactions.HacerClicEnElemento;
import com.screenplay.project.userinterface.OpenCartLoginPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ConfirmarLogin implements Task {
    @Override
    @Step("{0} confirma el inicio de sesión")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                HacerClicEnElemento.en(OpenCartLoginPage.BUTTON_LOGIN)
        );
    }

    public static ConfirmarLogin ahora() {
        return new ConfirmarLogin();
    }
}

