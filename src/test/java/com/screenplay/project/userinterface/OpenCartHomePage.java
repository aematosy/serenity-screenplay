package com.screenplay.project.userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class OpenCartHomePage extends PageObject {
    public static final Target BOTON_MI_CUENTA = Target.the("boton de mi cuenta").located(By.xpath("//span[text()='My Account']"));
    public static final Target ENLACE_REGISTRAR = Target.the("Enlace de Register").located(By.xpath("//a[text()='Register']"));

}
