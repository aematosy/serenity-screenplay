package com.screenplay.project.tasks;

import com.screenplay.project.hook.OpenWeb;
import com.screenplay.project.userinterface.OpenCartHomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.questions.Visibility;

import static com.screenplay.project.utils.Constants.TIME_SHORT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class RegistrarUsuarioOpenCart implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        // Esperar a que la página cargue completamente
        actor.attemptsTo(
                WaitUntil.the(OpenCartHomePage.BOTON_MI_CUENTA, isPresent())
                        .forNoMoreThan(30).seconds()
        );

        // Hacer scroll al elemento si es necesario
        actor.attemptsTo(
                Scroll.to(OpenCartHomePage.BOTON_MI_CUENTA)
        );

        // Esperar a que sea visible y clickeable
        actor.attemptsTo(
                WaitUntil.the(OpenCartHomePage.BOTON_MI_CUENTA, isVisible())
                        .forNoMoreThan(TIME_SHORT).seconds(),
                WaitUntil.the(OpenCartHomePage.BOTON_MI_CUENTA, isClickable())
                        .forNoMoreThan(TIME_SHORT).seconds()
        );

        // Hacer clic
        actor.attemptsTo(
                Click.on(OpenCartHomePage.BOTON_MI_CUENTA)
        );

        // Esperar y hacer clic en registrar
        actor.attemptsTo(
                WaitUntil.the(OpenCartHomePage.ENLACE_REGISTRAR, isVisible())
                        .forNoMoreThan(TIME_SHORT).seconds(),
                WaitUntil.the(OpenCartHomePage.ENLACE_REGISTRAR, isClickable())
                        .forNoMoreThan(TIME_SHORT).seconds(),
                Click.on(OpenCartHomePage.ENLACE_REGISTRAR)
        );
    }

    public static RegistrarUsuarioOpenCart SeleccionarMiCuenta() {
        return Tasks.instrumented(RegistrarUsuarioOpenCart.class);
    }
}