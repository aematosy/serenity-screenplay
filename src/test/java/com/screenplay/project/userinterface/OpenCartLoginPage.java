package com.screenplay.project.userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.findby.By;

public class OpenCartLoginPage extends PageObject {
    public static final Target INPUT_EMAIL = Target.the("Campo email").located(By.id("input-email"));
    public static final Target INPUT_PASSWORD = Target.the("Campo password").located(By.id("input-password"));
    public static final Target BUTTON_LOGIN = Target.the("Botón login").located(By.xpath("//input[@value='Login']"));
}
