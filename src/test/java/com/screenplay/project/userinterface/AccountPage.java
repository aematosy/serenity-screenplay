package com.screenplay.project.userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.findby.By;

public class AccountPage extends PageObject {

    public static final Target HEADER_MY_ACCOUNT = Target.the("Encabezado de la cuenta").located(By.xpath("//h2[normalize-space()='My Account']"));
}
