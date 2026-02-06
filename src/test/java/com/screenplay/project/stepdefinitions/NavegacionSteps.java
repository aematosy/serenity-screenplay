package com.screenplay.project.stepdefinitions;

import com.screenplay.project.tasks.OpenWeb;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class NavegacionSteps {

    private static final String TITULO_ESPERADO = "Your Store";

    @Given("{string} ingresa a la pagina de open cart")
    public void ingresaALaPaginaDeOpenCart(String nombreActor) {
        theActorCalled(nombreActor).attemptsTo(OpenWeb.abrirAplicacion());

        theActorInTheSpotlight().should(
                GivenWhenThen.seeThat("El título de la página contiene el texto esperado",
                        TheWebPage.title(),
                        Matchers.containsString(TITULO_ESPERADO))
        );
    }
}
