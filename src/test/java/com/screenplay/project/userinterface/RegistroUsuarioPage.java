package com.screenplay.project.userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class RegistroUsuarioPage extends PageObject {
    public static final Target CAMPO_NOMBRE = Target.the("campo de texto First Name").located(By.name("firstname"));
    public static final Target CAMPO_APELLIDO = Target.the("campo de texto Last Name").located(By.name("lastname"));
    public static final Target CAMPO_EMAIL = Target.the("campo de texto E-Mail").located(By.name("email"));
    public static final Target CAMPO_TELEFONO = Target.the("campo de texto Telephone").located(By.name("telephone"));
    public static final Target CAMPO_PASSWORD = Target.the("campo de texto Password").located(By.name("password"));
    public static final Target CAMPO_CONFIRMAR_PASSWORD = Target.the("campo de texto Confirm Password").located(By.name("confirm"));
    public static final Target CHECKBOX_ACEPTAR = Target.the("checkbox Agree").located(By.name("agree"));
    public static final Target BOTON_CONTINUAR = Target.the("botón Continue").located(By.xpath("//input[@type='submit' and @value='Continue']"));
}
