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
import net.serenitybdd.screenplay.actions.JavaScriptClick;


import static com.screenplay.project.utils.Constants.TIME_SHORT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class RegistrarUsuarioOpenCart implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        // Intentar con click normal primero
        try {
            System.out.println("Intentando primer click");
            actor.attemptsTo(
                    WaitUntil.the(OpenCartHomePage.BOTON_MI_CUENTA, isPresent())
                            .forNoMoreThan(30).seconds(),
                    Click.on(OpenCartHomePage.BOTON_MI_CUENTA)
            );
        } catch (Exception e) {
            // Si falla, usar JavaScript click
            System.out.println("Click normal falló, usando JavaScript click...");
            actor.attemptsTo(
                    JavaScriptClick.on(OpenCartHomePage.BOTON_MI_CUENTA)
            );
        }

        // Hacer clic en Register
        actor.attemptsTo(
                WaitUntil.the(OpenCartHomePage.ENLACE_REGISTRAR, isVisible())
                        .forNoMoreThan(15).seconds(),
                Click.on(OpenCartHomePage.ENLACE_REGISTRAR)
        );
    }

    public static RegistrarUsuarioOpenCart SeleccionarMiCuenta() {
        return Tasks.instrumented(RegistrarUsuarioOpenCart.class);
    }
}