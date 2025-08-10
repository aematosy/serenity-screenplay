package com.screenplay.project.userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class RegistroExitosoPage extends PageObject {
    public static final Target MENSAJE_EXITO = Target.the("mensaje de registro exitoso").locatedBy("//p[contains(text(),'Congratulations! Your new account has been successfully created!')]");

}
