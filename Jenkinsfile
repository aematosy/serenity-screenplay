pipeline {
    agent any

    parameters {
        choice(name: 'ESCENARIO', choices: ['@NuevoUsuario'], description: 'Selecciona el tag de Cucumber a ejecutar')
    }

    environment {
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
                bat 'mvn clean verify serenity:aggregate'
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