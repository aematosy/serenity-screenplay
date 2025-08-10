package com.screenplay.project.tasks;

import com.screenplay.project.userinterface.OpenCartHomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.actions.JavaScriptClick;


import static com.screenplay.project.utils.Constants.TIME_SHORT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class RegistrarUsuarioOpenCart implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            actor.attemptsTo(
                    WaitUntil.the(OpenCartHomePage.BOTON_MI_CUENTA, isPresent())
                            .forNoMoreThan(TIME_SHORT).seconds(),
                    Click.on(OpenCartHomePage.BOTON_MI_CUENTA)
            );
        } catch (Exception e) {
            actor.attemptsTo(
                    JavaScriptClick.on(OpenCartHomePage.BOTON_MI_CUENTA)
            );
        }

        // Hacer clic en Register
        actor.attemptsTo(
                WaitUntil.the(OpenCartHomePage.ENLACE_REGISTRAR, isVisible())
                        .forNoMoreThan(TIME_SHORT).seconds(),
                Click.on(OpenCartHomePage.ENLACE_REGISTRAR)
        );
    }

    public static RegistrarUsuarioOpenCart SeleccionarMiCuenta() {
        return Tasks.instrumented(RegistrarUsuarioOpenCart.class);
    }
}