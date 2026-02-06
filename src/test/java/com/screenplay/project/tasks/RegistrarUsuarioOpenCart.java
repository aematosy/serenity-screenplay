package com.screenplay.project.tasks;

import com.screenplay.project.interactions.EsperarElemento;
import com.screenplay.project.interactions.HacerClicEnElemento;
import com.screenplay.project.interactions.HacerClickRobusto;
import com.screenplay.project.userinterface.OpenCartHomePage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import static com.screenplay.project.utils.Constants.TIME_SHORT;

public class RegistrarUsuarioOpenCart implements Task {
    @Override
    @Step("{0} selecciona la opción Registrar Usuario desde Mi Cuenta")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                EsperarElemento.presente(OpenCartHomePage.BOTON_MI_CUENTA, TIME_SHORT),
                HacerClickRobusto.en(OpenCartHomePage.BOTON_MI_CUENTA),

                EsperarElemento.visible(OpenCartHomePage.ENLACE_REGISTRAR, TIME_SHORT),
                HacerClicEnElemento.en(OpenCartHomePage.ENLACE_REGISTRAR)
        );
    }

    public static RegistrarUsuarioOpenCart SeleccionarMiCuenta() {
        return Tasks.instrumented(RegistrarUsuarioOpenCart.class);
    }
}
