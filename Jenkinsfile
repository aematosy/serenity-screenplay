pipeline {
  agent any

  parameters {
    string(name: 'TAGS', defaultValue: '', description: 'Tag de Cucumber (opcional)')
  }

  options {
    timestamps()
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Prepare Workspace') {
      steps { bat 'if exist target rmdir /s /q target' }
    }

    stage('Resolve Dependencies') {
      steps { bat 'mvn -B -U -q dependency:go-offline' }
    }

    stage('Build') {
      steps { bat 'mvn -B -q -DskipTests clean package' }
    }

    stage('Run Tests') {
      steps {
        script {
          def tagArg = params.TAGS?.trim() ? "-Dcucumber.filter.tags=${params.TAGS.trim()}" : ""
          // Ejecuta Maven y NO detiene el pipeline si hay fallos
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
            echo "Pruebas con errores (exit code=${status}). Se continuará para generar reportes."
            currentBuild.result = 'UNSTABLE'
          }
        }
      }
    }

    stage('Aggregate Reports') {
      steps {
        // Fuerza la agregación aunque las pruebas hayan fallado
        bat 'mvn -B -q serenity:aggregate -DskipTests'
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
