package com.screenplay.project.tasks;

import com.screenplay.project.interactions.EscribirEnCampo;
import com.screenplay.project.interactions.HacerClicEnElemento;
import com.screenplay.project.models.Usuario;
import com.screenplay.project.userinterface.RegistroUsuarioPage;
import com.screenplay.project.utils.Utils;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class IngresarDatosUsuario implements Task {

    private final Usuario usuario;

    public IngresarDatosUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    @Step("{0} ingresa los datos del usuario {usuario}")
    public <T extends Actor> void performAs(T actor) {

        String correoAleatorio = usuario.getEmail().equalsIgnoreCase("aleatorio")
                ? Utils.generarEmailAleatorio()
                : usuario.getEmail();

        actor.attemptsTo(
                EscribirEnCampo.conValor(RegistroUsuarioPage.CAMPO_NOMBRE, usuario.getNombre()),
                EscribirEnCampo.conValor(RegistroUsuarioPage.CAMPO_APELLIDO, usuario.getApellido()),
                EscribirEnCampo.conValor(RegistroUsuarioPage.CAMPO_EMAIL, correoAleatorio),
                EscribirEnCampo.conValor(RegistroUsuarioPage.CAMPO_TELEFONO, usuario.getTelefono()),
                EscribirEnCampo.conValor(RegistroUsuarioPage.CAMPO_PASSWORD, usuario.getPassword()),
                EscribirEnCampo.conValor(RegistroUsuarioPage.CAMPO_CONFIRMAR_PASSWORD, usuario.getPassword()),
                HacerClicEnElemento.en(RegistroUsuarioPage.CHECKBOX_ACEPTAR)
        );
    }

    public static IngresarDatosUsuario conDatosDeUsuario(Usuario usuario) {
        return Tasks.instrumented(IngresarDatosUsuario.class, usuario);
    }
}
