pipeline {
  agent any

  options {
    timestamps()
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Prepare Workspace') {
      steps {
        bat 'if exist target rmdir /s /q target'
      }
    }

    stage('Resolve Dependencies') {
      steps {
        bat 'mvn -B -U -q dependency:go-offline'
      }
    }

    stage('Build') {
      steps {
        bat 'mvn -B -q -DskipTests clean package'
      }
    }

    stage('Run Tests') {
      steps {
        script {
          // Usa el parámetro definido en Jenkins UI: ESCENARIO (String)
          def tagArg = params.ESCENARIO?.trim() ? "-Dcucumber.filter.tags=${params.ESCENARIO.trim()}" : ""
          def status = bat(
            returnStatus: true,
            script: """
              mvn -B verify ^
                -Dserenity.test.environment=ci ^
                ${tagArg} ^
                -Dwebdriver.timeouts.implicitlywait=20000 ^
                -Dwebdriver.timeouts.fluentwait=40000 ^
                -Dwebdriver.wait.for.timeout=40000 ^
                -Dserenity.take.screenshots=FOR_FAILURES ^
                -Dserenity.restart.browser.each.scenario=true
            """
          )
          if (status != 0) {
            echo "Pruebas con errores (exit code=${status}). Se continuará generando reportes."
            currentBuild.result = 'UNSTABLE'
          }
        }
      }
    }

    stage('Aggregate Reports') {
      steps {
        script {
          def agg = bat(returnStatus: true, script: 'mvn -B -q serenity:aggregate -DskipTests')
          if (agg != 0) {
            echo "Falló serenity:aggregate (exit code=${agg})."
            if (currentBuild.result == null) { currentBuild.result = 'UNSTABLE' }
          }
        }
      }
    }

    stage('Publish Report') {
      steps {
        script {
          if (fileExists('target/site/serenity/index.html')) {
            publishHTML(target: [
              allowMissing: false,
              alwaysLinkToLastBuild: false,
              keepAll: true,
              reportDir: 'target/site/serenity',
              reportFiles: 'index.html',
              reportName: 'Serenity BDD Report'
            ])
          } else {
            bat 'dir target /s'
            echo 'Reporte de Serenity no encontrado'
            if (currentBuild.result == null) { currentBuild.result = 'UNSTABLE' }
          }
        }
      }
    }

    stage('Archive Artifacts') {
      steps {
        archiveArtifacts artifacts: 'target/site/serenity/**', fingerprint: true, onlyIfSuccessful: false
      }
    }
  }

  post {
    success  { echo 'OK' }
    unstable { echo 'Inestable' }
    failure  { echo 'Falló' }
    always   { echo 'Fin' }
  }
}
