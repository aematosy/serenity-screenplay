pipeline {
    agent any

    // Aquí defines los parámetros que aparecerán al "Construir con Parámetros"
    parameters {
        choice(name: 'ESCENARIO', choices: ['@NuevoUsuario', '@OtroTag', '@OtroMas'], description: 'Selecciona el tag de Cucumber a ejecutar')
    }

    environment {
        // Combina el entorno de CI con el tag seleccionado
        MAVEN_OPTS = "-Dserenity.test.environment=ci -Dcucumber.filter.tags=${params.ESCENARIO}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Obteniendo el código del repositorio...'
            }
        }
        stage('Build and Test') {
            steps {
                echo "Ejecutando pruebas con el tag: ${params.ESCENARIO}"
                sh 'mvn clean verify serenity:aggregate'
            }
        }
    }

    post {
        always {
            script {
                try {
                    publishHTML(target: [
                        allowMissing: false,
                        alwaysLinkToLastBuild: false,
                        keepAll: true,
                        reportDir: 'target/site/serenity',
                        reportFiles: 'index.html',
                        reportName: 'Serenity BDD Report'
                    ])
                } catch (Exception e) {
                    echo "No se pudo publicar el reporte. Error: ${e.getMessage()}"
                }
            }
        }
    }
}