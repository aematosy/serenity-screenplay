package com.screenplay.project.tasks;

import com.screenplay.project.models.Usuario;
import com.screenplay.project.userinterface.RegistroUsuarioPage;
import com.screenplay.project.utils.Utils;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class IngresarDatosUsuario implements Task {

    private final Usuario usuario;

    public IngresarDatosUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Generar correo aleatorio si es necesario
        String correoAleatorio = usuario.getEmail().equalsIgnoreCase("aleatorio")
                ? Utils.generarEmailAleatorio()
                : usuario.getEmail();

        // Realizar las acciones en la página
        actor.attemptsTo(
                Enter.theValue(usuario.getNombre()).into(RegistroUsuarioPage.CAMPO_NOMBRE),
                Enter.theValue(usuario.getApellido()).into(RegistroUsuarioPage.CAMPO_APELLIDO),
                Enter.theValue(correoAleatorio).into(RegistroUsuarioPage.CAMPO_EMAIL),
                Enter.theValue(usuario.getTelefono()).into(RegistroUsuarioPage.CAMPO_TELEFONO),
                Enter.theValue(usuario.getPassword()).into(RegistroUsuarioPage.CAMPO_PASSWORD),
                Enter.theValue(usuario.getPassword()).into(RegistroUsuarioPage.CAMPO_CONFIRMAR_PASSWORD),
                Click.on(RegistroUsuarioPage.CHECKBOX_ACEPTAR)
        );
    }

    public static IngresarDatosUsuario conDatosDeUsuario(Usuario usuario) {
        return Tasks.instrumented(IngresarDatosUsuario.class, usuario);
    }
}
