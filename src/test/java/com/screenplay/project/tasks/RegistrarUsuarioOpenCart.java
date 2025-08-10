package com.screenplay.project.tasks;

import com.screenplay.project.hook.OpenWeb;
import com.screenplay.project.userinterface.OpenCartHomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.screenplay.project.utils.Constants.TIME_SHORT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;

public class RegistrarUsuarioOpenCart implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(OpenCartHomePage.BOTON_MI_CUENTA, isEnabled()).forNoMoreThan(TIME_SHORT).seconds(),
                Click.on(OpenCartHomePage.BOTON_MI_CUENTA));
        actor.attemptsTo(Click.on(OpenCartHomePage.ENLACE_REGISTRAR));
    }

    public static RegistrarUsuarioOpenCart SeleccionarMiCuenta() {
        return Tasks.instrumented(RegistrarUsuarioOpenCart.class);
    }
}
