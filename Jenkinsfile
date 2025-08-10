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

                    def testResult = bat(
                        returnStatus: true,
                        script: """
                            mvn -B verify ${tagArg} ^
                                -Dserenity.test.environment=ci ^
                                -Dwebdriver.timeouts.implicitlywait=20000 ^
                                -Dserenity.take.screenshots=FOR_FAILURES
                        """
                    )
                    // Capturar el resultado para usarlo después
                    env.TEST_RESULT_CODE = testResult.toString()

                    if (testResult != 0) {
                        echo "Algunas pruebas fallaron - continuando para generar reportes"
                    }
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

                        echo 'Reporte publicado exitosamente'
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
                // Determinar estado final basado en el código de resultado de las pruebas
                if (env.TEST_RESULT_CODE != '0') {
                    echo 'Algunas pruebas fallaron - marcando como UNSTABLE'
                    currentBuild.result = 'UNSTABLE'
                } else {
                    echo 'Todas las pruebas pasaron'
                }
            }
        }

        success {
            echo 'Pipeline completado exitosamente - todas las pruebas pasaron'
        }

        unstable {
            echo 'Pipeline completado con advertencias - revisar pruebas fallidas'
        }

        failure {
            echo 'Pipeline falló - error en la ejecución'
        }
    }
}