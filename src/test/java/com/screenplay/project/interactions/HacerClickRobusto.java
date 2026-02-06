package com.screenplay.project.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class HacerClickRobusto implements Interaction {

    private final Target elemento;

    public HacerClickRobusto(Target elemento) {
        this.elemento = elemento;
    }

    @Override
    @Step("{0} hace click robusto en #elemento")
    public <T extends Actor> void performAs(T actor) {
        try {
            elemento.resolveFor(actor).click();
        } catch (Exception e) {
            WebDriver driver = net.serenitybdd.screenplay.abilities.BrowseTheWeb.as(actor).getDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", elemento.resolveFor(actor));
        }
    }

    public static HacerClickRobusto en(Target elemento) {
        return instrumented(HacerClickRobusto.class, elemento);
    }
}
