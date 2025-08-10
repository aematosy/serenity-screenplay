package com.screenplay.project.stepdefinitions;

import com.screenplay.project.models.Usuario;
import com.screenplay.project.questions.RegistroCorrecto;
import com.screenplay.project.tasks.FinalizarRegistro;
import com.screenplay.project.tasks.IngresarDatosUsuario;
import com.screenplay.project.tasks.RegistrarUsuarioOpenCart;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static com.screenplay.project.utils.Constants.ACTOR;
import static org.hamcrest.Matchers.equalTo;

public class RegistroUsarioSteps {

    @Before
    public void ConfiguracionInicial() {
        setTheStage(new OnlineCast());
    }

    @Given("ingreso a la opcion registrar")
    public void ingresoALOpcionRegistrar() {
        OnStage.theActorCalled(ACTOR).attemptsTo(RegistrarUsuarioOpenCart.SeleccionarMiCuenta());
    }

    @When("digito mi nombre {string}, apellido {string}, email {string}, telefono {string} y password {string}")
    public void digitoMisDatos(String nombre, String apellido, String email, String telefono, String password) {
        Usuario usuario = new Usuario(nombre,apellido,email,telefono,password);
        OnStage.theActorCalled(ACTOR).attemptsTo(IngresarDatosUsuario.conDatosDeUsuario(usuario));
    }

    @When("selecciono la opcion continuar")
    public void seleccionoOpcionContinuar() {
        OnStage.theActorCalled(ACTOR).attemptsTo(FinalizarRegistro.enElFormulario());

    }

    @Then("el sistema me debe mostrar la pantalla de registro exitoso")
    public void elSistemaDebeMostrarLaPantallaDeRegistroExitoso() {
        OnStage.theActorCalled(ACTOR).should(
                seeThat("El mensaje de éxito es visible", RegistroCorrecto.enLaPantalla(), equalTo(true))
        );

    }
}
