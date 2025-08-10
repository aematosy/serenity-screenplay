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
                // Determinar resultado basado en fallos de pruebas
                if (fileExists('target/failsafe-reports/')) {
                    def failedTests = bat(
                        returnStdout: true,
                        script: 'dir target\\failsafe-reports\\*FAILED*.txt 2>nul | find "File(s)" || echo 0 File(s)'
                    ).trim()

                    if (failedTests.contains('0 File(s)')) {
                        echo 'Todas las pruebas pasaron'
                    } else {
                        echo 'Algunas pruebas fallaron'
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }
    }
}