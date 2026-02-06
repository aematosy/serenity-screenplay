package com.screenplay.project.userinterface;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

@DefaultUrl("page:webdriver.base.url")
public class OpenCartHomePage extends PageObject {
    public static final Target BOTON_MI_CUENTA = Target.the("boton de mi cuenta").located(By.xpath("//a[@title='My Account' and contains(@class,'dropdown-toggle')]"));
    public static final Target ENLACE_REGISTRAR = Target.the("Enlace de Register").located(By.xpath("//ul[contains(@class,'dropdown-menu-right')]//a[normalize-space(.)='Register']"));
    public static final Target ENLACE_LOGIN = Target.the("Enlace de login").located(By.xpath("//ul[contains(@class,'dropdown-menu dropdown-menu-right')]//a[normalize-space(.)='Login']"));
}
