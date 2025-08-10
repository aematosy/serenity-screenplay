pipeline {
    agent any

    environment {
        // Combina el tag del parámetro con la propiedad de entorno 'ci' de serenity.conf
        MAVEN_OPTS = "-Dserenity.test.environment=ci -Dcucumber.filter.tags=${params.TAG_CUCUMBER}"
    }

    parameters {
        // Define el parámetro de elección aquí para que Jenkins lo muestre
        choice(name: 'TAG_CUCUMBER', choices: ['@NuevoUsuario', '@OtroTag', '@OtroMas'], description: 'Selecciona el tag de Cucumber a ejecutar')
    }

    stages {
        stage('Checkout') {
            steps {
                // Jenkins automáticamente extrae el código del repositorio
            }
        }
        stage('Build and Test') {
            steps {
                echo "Ejecutando pruebas con el tag: ${params.TAG_CUCUMBER}"
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