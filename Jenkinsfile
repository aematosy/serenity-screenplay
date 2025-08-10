pipeline {
    agent any

    parameters {
        choice(name: 'ESCENARIO', choices: ['@NuevoUsuario'], description: 'Selecciona el tag de Cucumber a ejecutar')
    }

    environment {
        MAVEN_OPTS = "-Dserenity.test.environment=ci -Dcucumber.filter.tags=${params.ESCENARIO} -Dwebdriver.timeouts.implicitlywait=20000"
        CHROME_BIN = "/usr/bin/google-chrome-stable"
        DISPLAY = ":99"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Obteniendo el código del repositorio...'
            }
        }

        stage('Setup Environment') {
            steps {
                echo 'Configurando entorno para Jenkins...'
                script {
                    // Verificar que Chrome esté disponible
                    try {
                        bat 'google-chrome --version || chrome --version || echo "Chrome no encontrado"'
                    } catch (Exception e) {
                        echo "Warning: No se pudo verificar Chrome: ${e.getMessage()}"
                    }
                }
            }
        }

        stage('Build and Test') {
            steps {
                echo "Ejecutando pruebas con el tag: ${params.ESCENARIO}"
                script {
                    try {
                        bat '''
                            mvn clean verify serenity:aggregate \
                            -Dserenity.test.environment=ci \
                            -Dcucumber.filter.tags=%ESCENARIO% \
                            -Dwebdriver.timeouts.implicitlywait=20000 \
                            -Dwebdriver.timeouts.fluentwait=40000 \
                            -Dwebdriver.wait.for.timeout=40000 \
                            -Dserenity.take.screenshots=FOR_FAILURES \
                            -Dserenity.restart.browser.each.scenario=true
                        '''
                    } catch (Exception e) {
                        echo "Test execution failed: ${e.getMessage()}"
                        // Continúa para generar reportes incluso si fallan las pruebas
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                try {
                    // Verificar que el directorio de reportes existe
                    if (fileExists('target/site/serenity/index.html')) {
                        publishHTML(target: [
                            allowMissing: false,
                            alwaysLinkToLastBuild: false,
                            keepAll: true,
                            reportDir: 'target/site/serenity',
                            reportFiles: 'index.html',
                            reportName: 'Serenity BDD Report'
                        ])
                        echo "✓ Reporte Serenity publicado exitosamente"
                    } else {
                        echo "⚠ No se encontró el archivo de reporte en target/site/serenity/"

                        // Listar contenido del directorio target para debug
                        try {
                            bat 'dir target /s'
                        } catch (Exception e) {
                            echo "No se pudo listar el directorio target"
                        }
                    }
                } catch (Exception e) {
                    echo "No se pudo publicar el reporte. Error: ${e.getMessage()}"
                }
            }
        }
        success {
            echo "Pipeline ejecutado exitosamente"
        }
        failure {
            echo "Pipeline falló"
        }
        unstable {
            echo "Pipeline inestable - algunas pruebas fallaron"
        }
    }
}