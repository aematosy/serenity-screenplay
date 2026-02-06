Feature: Login en OpenCart
  Como usuario
  Quiero iniciar sesión en el sistema
  Para acceder a mi cuenta

  Background:
    Given "Adrian" ingresa a la pagina de open cart
    And ingreso a la opcion iniciar sesion

    @Login
  Scenario Outline: Login exitoso con credenciales validas
    When igreso mis credenciales "<email>" y "<password>"
    And seleccion la opcion de iniciar sesion
    Then el sistema me debe mostrar la pantalla de mi cuenta
    Examples:
      | email                  | password |
      | test_nttdata@gmail.com | 123456   |