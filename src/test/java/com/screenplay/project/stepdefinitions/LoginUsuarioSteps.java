package com.screenplay.project.stepdefinitions;

import com.screenplay.project.questions.MiCuentaVisible;
import com.screenplay.project.tasks.AbrirLoginDesdeMiCuenta;
import com.screenplay.project.tasks.ConfirmarLogin;
import com.screenplay.project.tasks.IngresarCredenciales;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import static com.screenplay.project.utils.Constants.ACTOR;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class LoginUsuarioSteps {

    @Before
    public void setup(){
        OnStage.setTheStage(new OnlineCast());
    }

    @And("ingreso a la opcion iniciar sesion")
    public void ingreso_a_la_opcion_iniciar_session(){
        OnStage.theActorCalled(ACTOR).attemptsTo(
                AbrirLoginDesdeMiCuenta.desdeMiCuenta()
        );
    }

    @When("igreso mis credenciales {string} y {string}")
    public void ingresoMisCredenciales(String email, String password ){
        OnStage.theActorCalled(ACTOR).attemptsTo(
                IngresarCredenciales.con(email,password)
        );
    }

    @And("seleccion la opcion de iniciar sesion")
    public void Seleccion_la_opcion_iniciar_session(){
        OnStage.theActorCalled(ACTOR).attemptsTo(
                ConfirmarLogin.ahora()
        );
    }

    @Then("el sistema me debe mostrar la pantalla de mi cuenta")
    public void ElSistemaMeDebeMostrarLaPantallaDeMiCuenta(){
        OnStage.theActorCalled(ACTOR).should(
            seeThat("Se visualiza 'My Account'", MiCuentaVisible.enPantalla(), equalTo(true))
        );
    }


}
