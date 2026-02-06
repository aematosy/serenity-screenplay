package com.screenplay.project.questions;

import com.screenplay.project.userinterface.AccountPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class MiCuentaVisible implements Question<Boolean> {

    public Boolean answeredBy(Actor actor){
        return AccountPage.HEADER_MY_ACCOUNT.resolveFor(actor).isVisible();
    }

    public static MiCuentaVisible enPantalla(){
        return new MiCuentaVisible();
    }

}
