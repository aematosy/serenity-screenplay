package com.screenplay.project.questions;

import com.screenplay.project.userinterface.RegistroExitosoPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class RegistroCorrecto implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return RegistroExitosoPage.MENSAJE_EXITO.resolveFor(actor).isVisible();
    }

    public static RegistroCorrecto enLaPantalla() {
        return new RegistroCorrecto();
    }

}
