pipeline {
  agent any

  parameters {
    string(name: 'TAGS', defaultValue: '', description: 'Tag de Cucumber (opcional)')
  }

  options {
    timestamps()
    ansiColor('xterm')
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Prepare Workspace') {
      steps {
        bat 'echo Limpiando workspace'
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
          def tagArg = params.TAGS?.trim() ? "-Dcucumber.filter.tags=${params.TAGS.trim()}" : ""
          bat """
            mvn -B verify ^
              -Dserenity.test.environment=ci ^
              ${tagArg} ^
              -Dwebdriver.timeouts.implicitlywait=20000 ^
              -Dwebdriver.timeouts.fluentwait=40000 ^
              -Dwebdriver.wait.for.timeout=40000 ^
              -Dserenity.take.screenshots=FOR_FAILURES ^
              -Dserenity.restart.browser.each.scenario=true
          """
        }
      }
    }

    stage('Aggregate Reports') {
      steps {
        bat 'mvn -B serenity:aggregate'
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
            error 'Reporte de Serenity no encontrado'
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
