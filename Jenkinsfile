pipeline {
    agent any

    environment {
        MAVEN_OPTS = '-Xmx1024m'
    }

    options {
        timestamps()
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean & Build') {
            steps {
                bat '''
                    if exist target rmdir /s /q target
                    mvn -B -q clean compile test-compile
                '''
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def tagArg = params.ESCENARIO?.trim() ?
                        "-Dcucumber.filter.tags=${params.ESCENARIO.trim()}" : ""

                    bat """
                        mvn -B verify ${tagArg} ^
                            -Dserenity.test.environment=ci ^
                            -Dwebdriver.timeouts.implicitlywait=20000 ^
                            -Dserenity.take.screenshots=FOR_FAILURES ^
                            -Dmaven.test.failure.ignore=true
                    """
                }
            }
        }

        stage('Generate Report') {
            steps {
                bat 'mvn -B serenity:aggregate'
            }
        }

        stage('Publish') {
            steps {
                script {
                    if (fileExists('target/site/serenity/index.html')) {
                        publishHTML([
                            allowMissing: false,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'target/site/serenity',
                            reportFiles: 'index.html',
                            reportName: 'Serenity Report'
                        ])

                        archiveArtifacts(
                            artifacts: 'target/site/serenity/**',
                            allowEmptyArchive: true
                        )
                    } else {
                        error 'Reporte no generado'
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                // Verificar si hubo fallos basándose en el reporte de Serenity
                if (fileExists('target/site/serenity/index.html')) {
                    def buildLogContent = currentBuild.rawBuild.getLog(100).join('\n')

                    if (buildLogContent.contains('Tests failed') && buildLogContent.contains('| 1')) {
                        echo 'Algunas pruebas fallaron - marcando como UNSTABLE'
                        currentBuild.result = 'UNSTABLE'
                    } else if (buildLogContent.contains('Tests failed') && !buildLogContent.contains('| 0')) {
                        echo 'Pruebas fallaron - marcando como UNSTABLE'
                        currentBuild.result = 'UNSTABLE'
                    } else {
                        echo 'Todas las pruebas pasaron'
                    }
                } else {
                    echo 'No se encontró reporte de Serenity'
                    currentBuild.result = 'FAILURE'
                }
            }
        }

        success {
            echo 'Pipeline completado - todas las pruebas pasaron'
        }

        unstable {
            echo 'Pipeline completado con pruebas fallidas'
            echo 'Revisar el reporte de Serenity para detalles'
        }

        failure {
            echo 'Pipeline falló - error en la ejecución'
        }
    }
}