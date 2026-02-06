#Autor: Adrian Matos

Feature: Registro de nuevo usuario en OpenCart
  Como usuario
  Quiero registrar mis datos en el sistema
  Para crear una nueva cuenta en openCart
  #https://opencart.abstracta.us/index.php

  Background:
    Given "Adrian" ingresa a la pagina de open cart
    And ingreso a la opcion registrar

  @NuevoUsuario
  Scenario Outline: Ingresa los datos
    When digito mi nombre "<nombre>", apellido "<apellido>", email "<email>", telefono "<telefono>" y password "<password>"
    And selecciono la opcion continuar
    Then el sistema me debe mostrar la pantalla de registro exitoso
    Examples:
      | nombre  | apellido | email     | telefono  | password |
      | Adrian  | Matos    | aleatorio | 999888666 | 123456   |
