# Proyecto de Automatización con Serenity BDD y Screenplay

Este proyecto contiene pruebas de aceptación automatizadas para la aplicación OpenCart, utilizando Serenity BDD con el patrón Screenplay.

## 🚀 Tecnologías Utilizadas

*   **Java (JDK 11)**
*   **Serenity BDD**
*   **Maven**
*   **Cucumber**
*   **Screenplay Pattern**
*   **JUnit**
*   **Docker**
*   **Jenkins**

## 📋 Prerrequisitos

Asegúrate de tener instalado lo siguiente en tu sistema:

*   JDK 11 o superior.
*   Apache Maven 3.8 o superior.
*   Docker y Docker Compose.

## ⚙️ Instalación

1.  **Clona el repositorio:**
    ```bash
    git clone https://github.com/aematosy/serenity-screenplay
    cd screenplayDemo
    ```

2.  **Instala las dependencias de Maven:**
    Desde la raíz del proyecto, ejecuta el siguiente comando para descargar todas las dependencias y compilar el proyecto.
    ```bash
    mvn clean install
    ```

## ▶️ Ejecución de las Pruebas

### Ejecución Local

El proyecto está configurado para ejecutar pruebas utilizando perfiles de Maven, tags de Cucumber y entornos definidos en `serenity.conf`.

#### 1. Ejecutar todas las pruebas

Para ejecutar el conjunto completo de pruebas con la configuración por defecto (entorno `default`), usa el siguiente comando:

```bash
mvn clean verify
```

#### 2. Ejecutar pruebas por Tags

Puedes ejecutar escenarios o features específicos utilizando los tags de Cucumber. Pasa el tag a través de la propiedad `-Dtags`.

**Ejemplo:** Para ejecutar solo los escenarios marcados con `@NuevoUsuario`:

```bash
mvn clean verify -Dtags="@NuevoUsuario"
```

#### 3. Ejecutar pruebas por Entorno

El archivo `src/test/resources/serenity.conf` define varios entornos de ejecución (`default`, `dev`, `qa`, `prod`, `ci`, `browserstack`, `grid`). Para seleccionar un entorno, utiliza la propiedad `-Denvironment`.

**Ejemplo:** Para ejecutar las pruebas contra el entorno de **QA**:

```bash
mvn clean verify -Denvironment=qa
```

Esto usará la `webdriver.base.url` configurada para el entorno `qa` en `serenity.conf`.

#### 4. Ejecutar pruebas por Tag y Entorno

Puedes combinar las propiedades `-Dtags` y `-Denvironment` para una ejecución más específica.

**Ejemplo:** Ejecutar los tests de `@NuevoUsuario` en el entorno de **QA**:

```bash
mvn clean verify -Denvironment=qa -Dtags="@NuevoUsuario"
```

### Ejecución con Docker

El proyecto está configurado para ejecutar las pruebas en un entorno contenedorizado utilizando Docker y un Selenium Grid.

1.  **Levantar los servicios:**
    Desde la raíz del proyecto, ejecuta el siguiente comando para construir la imagen de pruebas y levantar el Selenium Hub, el nodo de Chrome y el contenedor de pruebas.
    ```bash
    docker-compose up --build
    ```
    Este comando ejecutará automáticamente las pruebas utilizando el entorno `grid` definido en `serenity.conf`, que apunta al Selenium Hub dentro de la red de Docker.

2.  **Ejecutar con tags en Docker:**
    Si deseas pasar tags específicos a la ejecución en Docker, puedes modificar el `CMD` en el `Dockerfile` o ajustar el `docker-compose.yml` para pasar argumentos al comando de Maven.

3.  **Ver los informes:**
    El `docker-compose.yml` está configurado para montar el volumen `./serenity-reports` en el directorio de informes del contenedor. Los informes de Serenity aparecerán en la carpeta `serenity-reports` en tu máquina local después de que la ejecución finalice.

4.  **Bajar los servicios:**
    Una vez finalizadas las pruebas, puedes detener y eliminar los contenedores con:
    ```bash
    docker-compose down
    ```

## ⚙️ Integración Continua con Jenkins

El archivo `Jenkinsfile` en la raíz del proyecto define un pipeline de CI/CD declarativo para automatizar la ejecución de las pruebas.

El pipeline realiza los siguientes pasos:
1.  **Checkout:** Clona el código fuente desde el repositorio.
2.  **Clean & Build:** Limpia el proyecto y compila el código.
3.  **Run Tests:** Ejecuta las pruebas de Serenity usando el entorno `ci` (configurado para ejecuciones headless en `serenity.conf`).
4.  **Generate Report:** Agrega los resultados de las pruebas en un informe de Serenity.
5.  **Publish:** Publica el informe HTML de Serenity en Jenkins para su visualización.

El pipeline está diseñado para ser ejecutado en un agente de Jenkins que tenga `bat` (Windows) disponible.

## 📊 Informes de Pruebas

Al finalizar la ejecución (local o en Docker), los informes de Serenity BDD se generarán en el siguiente directorio:

*   **Local:** `target/site/serenity/index.html`
*   **Docker:** `serenity-reports/index.html`

Abre el archivo `index.html` en tu navegador para ver un informe detallado de los resultados.

## 📂 Estructura del Proyecto

El proyecto sigue la siguiente estructura:

*   `src/test/java`: Contiene todo el código fuente de las pruebas.
    *   `com.screenplay.project.runners`: Clases JUnit para ejecutar los features de Cucumber.
    *   `com.screenplay.project.stepdefinitions`: Step Definitions que conectan los features con el código Java.
    *   `com.screenplay.project.questions`: Clases que representan preguntas sobre el estado de la aplicación.
    *   `com.screenplay.project.tasks`: Clases que encapsulan las interacciones del usuario.
    *   `com.screenplay.project.userinterface`: Page Objects que contienen los localizadores de la UI.
    *   `com.screenplay.project.hook`: Clases con la configuracion de los steps definitions
    *   `com.screenplay.project.interactions`: Clases que encapsulan las interacciones del usuario con la aplicación.
    *   `com.screenplay.project.models`: Clases que encapsulan los modelos de datos de la logica de negocio.
    *   `com.screenplay.project.utils`: Clases que encapsulan las utilidades que se usaran en el proyecto.
*   `src/test/resources`:
    *   `features`: Archivos `.feature` escritos en Gherkin.
    *   `serenity.conf`: Archivo de configuración principal de Serenity para gestionar entornos y drivers.
*   `pom.xml`: Define las dependencias del proyecto, plugins y perfiles de construcción de Maven.
*   `Dockerfile`: Define la imagen de Docker para ejecutar las pruebas.
*   `docker-compose.yml`: Orquesta los contenedores de Selenium Grid y de las pruebas.
*   `Jenkinsfile`: Define el pipeline de Integración Continua.